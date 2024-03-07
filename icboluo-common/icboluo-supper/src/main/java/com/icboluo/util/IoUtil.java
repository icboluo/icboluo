package com.icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author icboluo
 * @since 2024-03-07 22:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class IoUtil {

    /**
     * 默认文件大小1G,运算单位是字节 1024*1024*1024
     */
    public static final long DEFAULT_MAX_SIZE = 1073741824L;

    /**
     * 校验文件大小
     *
     * @param mf 文件
     */
    public static void validateFile(MultipartFile mf) {
        validateFile(mf, DEFAULT_MAX_SIZE);
    }

    /**
     * 校验文件大小
     *
     * @param mf   文件，单位为字节
     * @param size 文件限制大小
     */
    private static void validateFile(MultipartFile mf, long size) {
        if (mf == null || mf.isEmpty()) {
            throw new I18nException("file.is.empty");
        }
        if (mf.getSize() > size) {
            throw new I18nException("file.too.large");
        }
    }
}
