package com.icboluo.util.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.I18nException;
import com.icboluo.util.SpringUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>多个sheet页导入处理
 * <p>使用方式如下
 *
 * @author icboluo
 * @since 2024-04-02 0:32
 */
@Slf4j
public class MultipleSheetTemplate {

    private static final MessageSource MESSAGE_SOURCE = SpringUtil.getBean(MessageSource.class);
    List<ExcelListener<?>> listeners = new ArrayList<>();
    List<ExcelListener<?>> propertyListeners = new ArrayList<>();

    @Setter
    private boolean repeatDataThrow;

    /**
     * 增加监听器
     *
     * @param listener 监听器
     */
    public void add(ExcelListener<?> listener) {
        ListenerProxyFactory proxyFactory = new ListenerProxyFactory(listener);
        try {
            listeners.add((ExcelListener<?>) proxyFactory.getProxyInstance());
        } catch (Exception ex) {
            log.error("cglib proxy error", ex);
            throw new I18nException("system.error");
        }
        propertyListeners.add(listener);
    }

    /**
     * 增加监听器
     *
     * @param listenerList 监听器列表
     */
    public void add(List<ExcelListener<?>> listenerList) {
        listenerList.forEach(this::add);
    }

    /**
     * 多sheet导入刘婵
     *
     * @param mf 文件
     */
    public void handler(MultipartFile mf) throws IOException {
        // 首先校验文件是否是Excel文件
        before(mf);
        // 读取Excel,统一sheet模版校验
        setShowSheetName(true);
        setTemplateValidThrow(false);
        read(mf);
        // 统一注解校验，例如：NotEmpty，Length
        annoValid();
        if (repeatDataThrow) {
            repeatValid();
        }
        // 后序处理（例如校验和数据库是否匹配，入库
        after();
    }

    public void setShowSheetName(boolean flag) {
        listeners.stream()
                .filter(ValidHeadListener.class::isInstance)
                .map(ValidHeadListener.class::cast)
                .forEach(lis -> lis.setShowSheetName(flag));
    }

    public void setTemplateValidThrow(boolean flag) {
        listeners.stream()
                .filter(ValidHeadListener.class::isInstance)
                .map(ValidHeadListener.class::cast)
                .forEach(lis -> lis.setTemplateValidThrow(flag));
    }

    /**
     * 导入前处理
     *
     * @param mf 文件
     */
    public void before(MultipartFile mf) {
        ExcelUtil.xlsAndXlsxNameValid(mf);
    }

    /**
     * 读取文件（实测10万行数据导入总耗时16s，监听器耗时11s（起重工注解校验耗时6s
     *
     * @param mf 文件
     * @throws IOException io异常
     */
    public void read(MultipartFile mf) throws IOException {
        List<ReadSheet> readSheetList = new ArrayList<>();
        for (int i = 0; i < listeners.size(); i++) {
            ExcelListener<?> listener = listeners.get(i);
            ReadSheet readSheet = EasyExcelFactory.readSheet(i)
                    .head(listener.clazz)
                    .headRowNumber(listener.getHead())
                    .registerReadListener(listener)
                    .build();
            readSheetList.add(readSheet);
        }
        try (InputStream is = mf.getInputStream(); ExcelReader er = EasyExcelFactory.read(is).build()) {
            er.read(readSheetList);
        }
        String headErrorMsg = listeners.stream()
                .filter(ValidHeadListener.class::isInstance)
                .map(lis -> ((ValidHeadListener<?>) BeanUtil.cast(lis)).getHeadErrorMsg())
                .filter(StringUtils::hasText)
                .collect(Collectors.joining("<br/>"));
        if (!headErrorMsg.isEmpty()) {
            throw new I18nException(headErrorMsg);
        }
    }

    /**
     * 注解校验
     */
    public void annoValid() {
        String msgs = listeners.stream()
                .filter(ValidHeadBodyListener.class::isInstance)
                .map(ValidHeadBodyListener.class::cast)
                .map(listener -> listener.getErrorMsg(true))
                .filter(StringUtils::hasText)
                .collect(Collectors.joining("<br><br/>"));
        if (StringUtils.hasText(msgs)) {
            throw new I18nException(msgs);
        }
    }

    /**
     * 重复数据处理
     *
     * @see ExcelListener#getDistinctList(Function)  获取去重后的数据可以使用此方法
     */
    public void repeatValid() {

    }

    /**
     * after函数可以维护导入后的后序处理刘婵，例如：插入数据库
     *
     * @see ExcelListener#doAfterAllAnalysed(AnalysisContext) excel导入后的后续处理，本来该方法为单个sheet读取完成后就进行处理，
     * 此块将其功能代理成全部sheet导入后统一处理
     */
    private void after() {
        for (ExcelListener<?> listener : propertyListeners) {
            listener.doAfterAllAnalysed(null);
        }
    }
}
