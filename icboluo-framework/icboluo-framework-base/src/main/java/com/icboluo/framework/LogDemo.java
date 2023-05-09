package com.icboluo.framework;

import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 如果日志是红色的，说明某个地方将日志捕获，仅仅做了打印操作（e.printStackTrace();没有进行抛出
 * <p> slf4j simple logging facade for java (java门面日志) 类似于一个接口，其实现类有：
 * <p> logback 是spring默认日志插件、log4j2 log4j的更新版本，效率更优、slf4j.simple
 *
 * @author icboluo
 * @since 2020/12/2 20:52
 */
@Slf4j
public class LogDemo {
    public static void main(String[] args) {
        log.error("错误,只有一行，不会抛出异常");
        log.error("记录一行error，抛出错误日志", new IcBoLuoException());
//        log.error("记录一行error，抛出错误日志", e);
/*
        低级别是指不重要的级别，打印日志打印的是打印当前级别及以上
        日志的级别从高到低：error->warn->info->debug
        spring 打印日志的默认级别：info 及其 以上
       日志最差实践：logging->level->root->debug：这种写法会将所有的debug级别以上的日志打印出来，不管是内部还是外部包，例如redis包的日志
       如果要对包进行日志级别设置，并对包内的子包进行其他日志级别设置，可以使用 / *: debug写法
       需要注意：项目的启动模式和日志级别是毫无关系的
*/
        String str = """
                * 对于问题点，需要注意error级别的日志，尤其是单行error日志
                                
                * 出现异常之后，异常点在哪里就调试哪里，比如 easy excel 报json array not convert，就应该提供json array 的支持，
                  而不是对list类型的字段提供list convert支持，这同样要求系统报错打印error级别的日志尤其重要
                                
                * 日志功能建议使用切面、注解去处理，同样也可以处理权限问题
                                
                日志全量记录和只记录更新内容有下面的不同
                                
                * 全量记录会记录所有的内容，数据比较齐全，不用链式搜索日志数据/回溯简单
                * 更新记录只记录部分数据，不会造成数据量过大，但是查找部分数据会需要找上一个日志的记录，比较繁琐/数据量比较少
                """;
        log.warn(str);
        String str1 = """
                logback日志的路径记录相对路径是比较合适的，log； /log绝对路径不起作用 ../log也不行
                """;
        // 对于异常来说：runtimeException super(msg)，会生成一行 className:msg的error日志
        log.info(str1);
    }
}
