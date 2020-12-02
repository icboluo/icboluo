package com.icboluo;

import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author icboluo
 * @date 2020/12/2 20:52
 */
@Slf4j
public class LogDemo {
    public static void main(String[] args) {
        log.error("错误");
        log.error("记录一行error，抛出错误日志", new IcBoLuoException());
    }
}
