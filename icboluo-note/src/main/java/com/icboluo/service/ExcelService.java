package com.icboluo.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.icboluo.annotation.EasyExcelAlias;
import com.icboluo.component.ReadExcelEntity;
import com.icboluo.component.WriteExcelEntity;
import com.icboluo.mapper.ColumnMapper;
import com.icboluo.object.businessobject.RowBO;
import com.icboluo.object.businessobject.SheetBO;
import com.icboluo.object.clientobject.RowCO;
import com.icboluo.object.dataobject.RowDO;
import com.icboluo.object.viewobject.RowVO;
import com.icboluo.object.viewobject.SheetVO;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.ExcelHelper;
import com.icboluo.util.FileHelper;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.listenter.HeadDataListener;
import com.icboluo.util.listenter.RowDataListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author icboluo
 */
@Service
@Transactional
@Slf4j
public class ExcelService {
    @Resource
    private ColumnMapper excelMapper;
    @Resource
    private ReadExcelEntity readExcelEntity;
    @Resource
    private WriteExcelEntity writeExcelEntity;

    /**
     * 数据库文档读成建表语句
     *
     * @param filePathName 文件地址+名称
     * @param sheet        sheet的名称
     */
    public void readDbDocument(String filePathName, String sheet) {
        List<SheetBO> list = this.readExcel(filePathName, sheet);
        this.toSql(list);
    }

    /**
     * 把数据库的内容写成excel
     *
     * @param database 数据库名称
     * @param table    表名
     */
    public void write(String database, String table) {
        if (StringUtils.isEmpty(database)) {
            throw new IcBoLuoException("数据库名称为空，不能查询数据库数据");
        }
        List<SheetVO> list = readDatabase(database, table);
        this.toExcel(list);
    }


    /**
     * 将lists数据放到bo中
     *
     * @param sheetBO sheet对象
     * @param rowCOS  一个sheet
     */
    private void buildExcelBO(SheetBO sheetBO, List<RowCO> rowCOS) {
        List<RowBO> list = new ArrayList<>();
        for (RowCO rowCO : rowCOS) {
            RowBO rowBO = BeanHelper.copyProperties(rowCO, RowBO.class);
            list.add(rowBO);
        }
        sheetBO.setList(list);
    }

    /**
     * 读数据库数据
     *
     * @param database 数据库名称
     * @return sheet集合
     * @throws IcBoLuoException 业务异常的情况下抛出
     */
    private List<SheetVO> readDatabase(String database, String table) {
        List<String> tables = excelMapper.selectTableNameByDatabase(database);
        if (CollectionUtils.isEmpty(tables)) {
            throw new IcBoLuoException("数据库" + database + "中没有表");
        }
        if (!StringUtils.hasText(table)) {
            if (tables.contains(table)) {
                List<RowDO> data = excelMapper.selectTableConstruction(database, table);
                SheetVO sheetVO = new SheetVO();
                sheetVO.setList(BeanHelper.copyWithCollection(data, RowVO.class));
                return Collections.singletonList(sheetVO);
            } else {
                throw new IcBoLuoException("数据库" + database + "中没有" + table + "数据库");
            }
        }
        List<RowDO> excelDOS = excelMapper.selectDatabaseConstruction(database, tables);
        Map<String, List<RowDO>> map = excelDOS.stream()
                .collect(Collectors.groupingBy(RowDO::getTableName));
        List<SheetVO> list = new ArrayList<>();
        for (Map.Entry<String, List<RowDO>> entry : map.entrySet()) {
            String tableName = entry.getKey();
            List<RowDO> v = entry.getValue();
            SheetVO sheetVO = new SheetVO();
            sheetVO.setSheetName(tableName);
            sheetVO.setList(BeanHelper.copyWithCollection(v, RowVO.class));
            list.add(sheetVO);
        }
        return list;
    }

    /**
     * 将excel对象生成excel
     *
     * @param sheetVOS excel对象
     */
    private void toExcel(List<SheetVO> sheetVOS) {
        if (CollectionUtils.isEmpty(sheetVOS)) {
            throw new IcBoLuoException("让生成空的？？？");
        }
        List<SheetVO> sheetList = sheetVOS.stream()
                .sorted(Comparator.comparing(SheetVO::getSheetName))
                .toList();
        WriteSheet writeSheet;
        String fileName = writeExcelEntity.getExcelPath();
//        registerConverter 可以注册转换器，对java数据和excel数据的转换做约定，例如 local date time 的转换
        ExcelWriter excelWriter = EasyExcelFactory.write(fileName).build();
        for (int i = 0; i < sheetList.size(); i++) {
            SheetVO sheetVO = sheetList.get(i);

            HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelHelper.setCellStyle();
            writeSheet = EasyExcelFactory.writerSheet(i, sheetVO.getSheetName())
                    .registerWriteHandler(horizontalCellStyleStrategy).head(RowVO.class).build();
            List<RowVO> list = sheetVO.getList();
            Stream<RowVO> stream1 = list.stream()
                    .filter(r -> RowBO.isPrimaryKey(r.getColumnKey()))
                    .sorted(Comparator.comparing(RowVO::getColumnName));
            Stream<RowVO> stream2 = list.stream()
                    .filter(r -> !RowBO.isPrimaryKey(r.getColumnKey()))
                    .sorted(Comparator.comparing(RowVO::getColumnName));
            List<RowVO> collect = Stream.concat(stream1, stream2)
                    .toList();
            excelWriter.write(collect, writeSheet);
        }
        if (excelWriter == null) {
            throw new IcBoLuoException("走这就完蛋了");
        }
//        主要不是为了关闭流，写的时候必须write，否则写不出来，write的时候必须finish
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 将excel容器生成sql
     *
     * @param list 一个workbook生成的对象
     */
    private void toSql(List<SheetBO> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IcBoLuoException("读到的workbook都是空的");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SET NAMES utf8mb4;").append("\n");
        //取消外键约束
        sb.append("SET FOREIGN_KEY_CHECKS = 0;").append("\n");
        for (SheetBO sheetBO : list) {
            sb.append(objToSql(sheetBO));
            sb.append("\n");
        }
        //激活外键约束
        sb.append("SET FOREIGN_KEY_CHECKS = 1;").append("\n");
        FileHelper.deleteFile(readExcelEntity.getGeneralSqlPath());
        FileHelper.write(sb.toString(), readExcelEntity.getGeneralSqlPath());
    }

    private static final String SET_ASC = "    SET = utf8 COLLATE = utf8_general_ci    ";

    /**
     * java对象数据转换成建表语句
     */
    private StringBuilder objToSql(SheetBO excel) {
        StringBuilder s = new StringBuilder();
        s.append("DROP TABLE IF EXISTS   `").append(excel.getTableName()).append("`;\n");
        s.append("CREATE TABLE  ").append("`").append(excel.getTableName()).append("`   ").append("(\n");
        List<RowBO> list = excel.getList();
        Objects.requireNonNull(list, "所建数据表不能为null");
        for (RowBO row : list) {
            String comment = buildComment(row.getColumnComment());
            row.setColumnComment(comment);
            s.append("`").append(row.getColumnName()).append("` ");
            s.append(row.getColumnType()).append("  ");
            //是主键 或者 是非空的时候才设置 not null
            boolean isPrimaryKey = RowBO.isPrimaryKey(row.getIsPrimaryKey());
            boolean isNull = row.isNull(row.getIsNullable());
            if (isPrimaryKey || !isNull) {
                s.append("NOT   NULL  ");
            }
            if (StringUtils.hasText(row.getColumnDefault())) {
                //不是主键和是可以为空的情况下才可以设置默认空值
                if (!isPrimaryKey && isNull) {
                    s.append("DEFAULT NULL  ");
                }
            } else {
                if ("CURRENT_TIMESTAMP".equals(row.getColumnDefault())) {
                    s.append("DEFAULT   ").append(row.getColumnDefault()).append("  ");
                } else {
                    s.append("DEFAULT   ").append("'").append(row.getColumnDefault()).append("'  ");
                }
            }
            if (!StringUtils.hasText(row.getColumnComment())) {
                s.append("COMMENT   ").append("'").append(row.getColumnComment()).append("'");
            }
            s.append(",\n");
        }
        List<String> ids = list.stream()
                .filter(r -> RowBO.isPrimaryKey(r.getIsPrimaryKey()))
                .map(RowBO::getColumnName)
                .toList();
        if (!CollectionUtils.isEmpty(ids)) {
            s.append("PRIMARY KEY   ").append("(");
            ids.forEach(id -> s.append("`").append(id).append("`,  "));
            s.append(")   ")
                    .append("USING BTREE\n");
        }
        s.deleteCharAt(s.lastIndexOf(","));
        s.append(")").append("ENGINE = InnoDB CHARACTER     ").append(SET_ASC)
                .append("COMMENT = '").append(excel.getTableName()).append("'   ;");
        return s;
    }

    private String buildComment(String s) {
        if (StringUtils.hasText(s)) {
            return null;
        }
        return s.replace("'", "\\'");
    }

    /**
     * 把excel数据读成容器数据
     *
     * @param excelPath 文件地址+名称
     * @param sheet     sheet名称
     * @return 保存excel数据的容器
     */
    public List<SheetBO> readExcel(String excelPath, String sheet) {
        HeadDataListener headListener = readHead(excelPath);
        Class<RowCO> clazz = RowCO.class;
        this.buildClass(headListener.getHead(), clazz);

        List<SheetBO> list = new ArrayList<>();
        ExcelReader er = EasyExcelFactory.read(excelPath).build();
        List<ReadSheet> readSheets = er.excelExecutor().sheetList();
        for (int i = 0; i < readSheets.size(); i++) {
            RowDataListener listener = new RowDataListener();
            ReadSheet rs = EasyExcelFactory
                    .readSheet(i)
                    .head(RowCO.class)
                    .registerReadListener(listener)
                    .build();
            er.read(rs);
            er.finish();

            SheetBO sheetBO = new SheetBO();
            String sheetName = readSheets.get(i).getSheetName();
            sheetBO.setTableName(sheetName);
            List<RowCO> rowClients = listener.list;
            this.buildExcelBO(sheetBO, rowClients);
            list.add(sheetBO);
        }
        return list;
    }

    private HeadDataListener readHead(String excelPath) {
        ExcelReader er = EasyExcelFactory.read(excelPath).build();
        HeadDataListener listener = new HeadDataListener();
        ReadSheet rs = EasyExcelFactory
                .readSheet(0)
                .headRowNumber(0)
                .registerReadListener(listener)
                .build();
        er.read(rs);
        er.finish();
        return listener;
    }

    @SneakyThrows({NoSuchFieldException.class, IllegalAccessException.class})
    private <T> void buildClass(List<String> head, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < head.size(); i++) {
            String cell = head.get(i);
            boolean haveMatch = false;
            for (Field field : fields) {
                EasyExcelAlias eea = field.getAnnotation(EasyExcelAlias.class);
                String[] alias = eea.alias();
                boolean isContains = Arrays.asList(alias).contains(cell);
                if (!isContains) {
                    continue;
                }
                ExcelProperty ep = field.getAnnotation(ExcelProperty.class);
                //获取 ep 这个代理实例所持有的 InvocationHandler
                InvocationHandler ih = Proxy.getInvocationHandler(ep);
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                Field hField = ih.getClass().getDeclaredField("memberValues");
                // 因为这个字段事 private final 修饰，所以要打开权限
                hField.setAccessible(true);
                // 获取 memberValues
                Map memberValues = (Map) hField.get(ih);
                memberValues.put("index", i);
                haveMatch = true;
                break;
            }
            if (!haveMatch) {
                log.error("{}不匹配", cell);
            }
        }
    }

    public void write2(String database, String tableName) {
    }
}
