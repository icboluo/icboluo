package com.icboluo.util;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author icboluo
 * @since 2025-10-21 22:00
 */
public class ZipUtil {
    public static void zipParse(File zip, Consumer<File> zipParse) {
        File parentDir = new File(zip.getParent(), UUID.randomUUID().toString());
        File unzip = cn.hutool.core.util.ZipUtil.unzip(zip, parentDir);
        try {
            zipParse.accept(unzip);
        } finally {
            FileUtil.del(unzip);
        }
    }
}
