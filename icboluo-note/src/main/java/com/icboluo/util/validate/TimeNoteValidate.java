package com.icboluo.util.validate;

import com.icboluo.object.client.TimeNoteCO;
import com.icboluo.util.BeanHelper;
import com.icboluo.util.IcBoLuoException;
import org.springframework.stereotype.Component;

/**
 * @author icboluo
 */
@Component
public class TimeNoteValidate {

    public void validate(TimeNoteCO obj) {
        if (BeanHelper.allIsNull(obj.getProblem(), obj.getResult(), obj.getBelongToScope())) {
            throw new IcBoLuoException();
        }
    }
}
