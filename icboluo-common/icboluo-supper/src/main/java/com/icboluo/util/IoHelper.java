package com.icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 资源处理工具类
 *
 * @author icboluo
 * @since 2024-03-07 22:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class IoHelper {

    /**
     * <p>默认文件大小1G,运算单位是字节 1024*1024*1024
     * <p>1G为 1073741824 ，1M为 1048576；可以作为参考
     */
    public static final long DEFAULT_MAX_SIZE = 1073741824L;

    /**
     * 校验文件大小
     *
     * @param mf 文件
     * @throws I18nException 校验失败抛出此异常
     */
    public static void validateFile(MultipartFile mf) {
        validateFile(mf, DEFAULT_MAX_SIZE);
    }

    /**
     * 校验文件大小
     *
     * @param mf   文件，单位为字节 MultipartFile 在部分情况下是支持重复读取的
     * @param size 文件限制大小
     * @throws I18nException 校验失败抛出此异常
     */
    public static void validateFile(MultipartFile mf, long size) {
        if (mf == null || mf.isEmpty()) {
            throw new I18nException("file.is.empty");
        }
        if (mf.getSize() > size) {
            throw new I18nException("file.too.large");
        }
    }

    public static boolean validateFileName(MultipartFile mf, String... suffixArr) {
        return validateFileName(mf.getOriginalFilename(), suffixArr);
    }

    public static boolean validateFileName(String originalFilename, String... suffixArr) {
        if (originalFilename == null) {
            return false;
        }
        for (String suffix : suffixArr) {
            if (originalFilename.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    public static void copy(MultipartFile mf, File file) throws IOException {
        Files.copy(mf.getInputStream(), file.toPath());
    }
}
