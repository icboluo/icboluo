package com.icboluo.util.excel;

import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author icboluo
 * @since 2025-09-16 21:19
 */
@Data
public class ExcelData<T> {
    private T data;

    private int rowNo;

    private String rowMsg;

    private String[] cellMsg;

    private boolean rowEmpty;

    public boolean isSuccess() {
        if (rowEmpty || rowMsg != null) {
            return false;
        }
        if (cellMsg == null) {
            return true;
        }
        for (String cm : cellMsg) {
            if (cm != null) {
                return false;
            }
        }
        return true;
    }

    public Optional<String> rowError(MessageSource messageSource) {
        if (rowMsg == null) {
            return Optional.empty();
        }
        String rowMessage = messageSource.getMessage("row.{0}.error.{1}",
                new Object[]{rowNo, rowMsg}, LocaleContextHolder.getLocale());
        return Optional.of(rowMessage);
    }

    public List<String> cellError(MessageSource messageSource) {
        List<String> msg = new ArrayList<>();
        for (int j = 0; j < cellMsg.length; j++) {
            if (cellMsg[j] != null) {
                String message = messageSource.getMessage("row.{0}.col.{1}.error.{2}",
                        new Object[]{rowNo, ExcelUtil.convertToTitle(j + 1), cellMsg[j]},
                        LocaleContextHolder.getLocale());
                msg.add(message);
            }
        }
        return msg;
    }
}
