package com.icboluo.util.validate;

import com.icboluo.object.clientobject.TimeNoteCO;
import com.icboluo.util.IcBoLuoException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author icboluo
 */
@Component
public class TimeNoteValidate {

    public void validate(TimeNoteCO obj) {
        boolean isEmptyData = StringUtils.hasText(obj.getBelongToScope())
                && StringUtils.hasText(obj.getProblem()) && StringUtils.hasText(obj.getResult());
        if (isEmptyData) {
            throw new IcBoLuoException();
        }
    }
}
