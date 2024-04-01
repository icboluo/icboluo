package com.icboluo.util.excel;

import com.icboluo.util.I18nException;
import com.icboluo.util.SpringUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;

/**
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
}
