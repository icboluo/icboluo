package com.icboluo;

import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;

/**
 * slf4j simple logging facade for java (java门面日志) 类似于一个接口，其实现类有：
 * logback spring默认日志插件、log4j2 log4j的更新版本，效率更优
 *
 * @author icboluo
 * @date 2020/12/2 20:52
 */
@Slf4j
public class LogDemo {
    public static void main(String[] args) {
        log.error("错误,只有一行，不会抛出异常");
        log.error("记录一行error，抛出错误日志", new IcBoLuoException());
//        log.error("记录一行error，抛出错误日志", e);
/*
        日志的级别从高到低：error->warn->info->error
        spring 打印日志的默认级别：info
       日志最差实践：logging->level->root->debug：这种写法会将所有的debug级别以上的日志打印出来，不管是内部还是外部包，例如redis包的日志
       如果要对包进行日志级别设置，并对包内的子包进行其他日志级别设置，可以使用 / *: debug写法
       需要注意：项目的启动模式和日志级别是毫无关系的
*/

    }
}
