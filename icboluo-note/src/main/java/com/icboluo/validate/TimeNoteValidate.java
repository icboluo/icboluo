package com.icboluo.validate;

import com.icboluo.object.clientobject.TimeNoteCO;
import com.icboluo.util.IcBoLuoException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author icboluo
 */
@Component
public class TimeNoteValidate implements  Validator {

    public void validate(TimeNoteCO obj) {
        boolean isEmptyData = StringUtils.isEmpty(obj.getBelongToScope()) && StringUtils.isEmpty(obj
                .getProblem()) && StringUtils.isEmpty(obj.getResult());
        if (isEmptyData) {
            throw new IcBoLuoException();
        }
    }
}
