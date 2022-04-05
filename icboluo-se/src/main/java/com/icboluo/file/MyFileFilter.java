package com.icboluo.file;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileFilter;

/**
 * 自定义文件过滤器类
 */
@AllArgsConstructor
class MyFileFilter implements FileFilter {

    private final String suffix;

    /**
     * ctrl + i  或者 alt + enter 去实现
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(suffix));
    }
}
