package com.icboluo.constant;

import java.text.MessageFormat;
import java.util.function.Function;

/**
 * @author icboluo
 * @date 2021-10-25 23:44
 */
public interface MqConstant {

    String SEND_MESSAGE = "simple_queue";

    String QUEUE_NAME_SIMPLE = "simple_queue";

    String QUEUE_NAME_WORK = "work_queue";

    Function<Integer, String> MSG = i -> MessageFormat.format("task {0} is ready send", i);
}
