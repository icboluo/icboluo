package com.icboluo.constant;

/**
 * <p>文件相对路径
 * <p>在微服务项目中，文件的相对路径针对于整个微服务，而不是本服务
 * <p>/   文件根目录   参考的是盘符目录
 * <p>./   同级目录     和不加 / 的作用是一致的   参考的是当前文件夹
 * <p>../   父级目录       参考的也是文件夹
 *
 * @author icboluo
 * @since 2022-04-05 14:37
 */
public interface FileRelativePathPre {

    String NOTE = "./icboluo-note/";
    String SE = "./icboluo-se/";

    String RESOURCES = "src/main/resources/";
}
