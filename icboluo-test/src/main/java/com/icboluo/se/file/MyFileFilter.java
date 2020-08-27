package com.icboluo.se.file;

import java.io.File;
import java.io.FileFilter;

/**
 * 自定义文件过滤器类
 */
class MyFileFilter implements FileFilter {

    private final String suffix;

    // 构造方法
    public MyFileFilter(String suffix) {
        this.suffix = suffix;
    }

    /**
     * ctrl + i  或者 alt + enter 去实现
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(suffix));
    }
}
