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
 * <p>使用方式如下：
 * <pre>
 *     {@code MultipleSheetTemplate multipleSheet = new MultipleSheetTemplate();
 *          ValidHeadBodyListener<A> aListener = new ValidHeadBodyListener<>(A.class);
 *          ValidHeadBodyListener<B> bListener = new ValidHeadBodyListener<>(B.class);
 *          multipleSheet.add(aListener);
 *          multipleSheet.add(bListener);
 *          multipleSheet.handler(mf);
 *     }<pre/>
 *
 * 该类设计的目的是想采用模版方法模式，使用设计模式Template为后缀做标识；希望可以通过重写子类，实现自定义控制导入流程
 * 所以本类大部分方法为public，目的就是方便重写
 * 不过建类是繁琐的，本类提供了一套默认实现，方便调用（因此本类没有设计为抽象类
 * @author icboluo
 * @since 2024-04-02 0:32
 */
@Slf4j
public class MultipleSheetTemplate {

    private static final MessageSource MESSAGE_SOURCE = SpringUtil.getBean(MessageSource.class);
    /**
     * <p>监听器列表，其中index代表对应sheet的序号，此监听器存入的是cglib代理对象
     * <p> 此块使用动态代理的目的是代理EasyExcel原生的doAfterAnalysed方法，使该方法可以在所有的sheet页处理完成后统一调用
     *
     * @see ExcelListener#doAfterAllAnalysed(AnalysisContext)
     * @see ValidHeadListener 校验表头的监听器
     * @see ValidHeadBodyListener 校验表头、表体的监听器
     */
    List<ExcelListener<?>> listeners = new ArrayList<>();
    /**
     * 因为cglib代理仅仅代理了方法，属性没有被代理；所以专门增加了一个和被代理读写一一对应的源对象
     */
    List<ExcelListener<?>> propertyListeners = new ArrayList<>();
    /**
     * 重复数据是否抛异常，如果为true代表重复数据抛异常；如果为否，代表不处理
     *
     * @see ExcelListener#getDistinctList(Function) 发现重复数据的时候，如果需要取最后一条，可以使用该方法
     */
    @Setter
    private boolean repeatDataThrow;

    /**
     * <p>增加监听器
     * <p> 注意：因为此处使用的是cglib代理，所以传入的监听器必须拥有无参构造
     *
     * @param listener 监听器
     */
    public void add(ExcelListener<?> listener) {
        ListenerProxyFactory proxyFactory = new ListenerProxyFactory(listener);
        try {
            // 奇怪，此处如果抛异常，为什么需要显示捕获，否则会丢失堆栈异常
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
    public void add(List<? extends ExcelListener<?>> listenerList) {
        listenerList.forEach(this::add);
    }

    /**
     * 多sheet导入流程
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
        // 统一Valid注解校验，例如：NotEmpty，Length
        annoValid();
        // 统一重复数据处理（可以重复数据统一异常提示，或者以最后一条为主
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
     * 读取文件（实测10万行数据导入总耗时16s，监听器耗时11s（其中注解校验耗时6s
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
     * after函数可以维护导入后的后序处理流程，例如：插入数据库
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
