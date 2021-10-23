package icboluo.enumerate;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020/11/12 20:46
 */
@AllArgsConstructor
public enum LanguageEnum {
    /**
     * english
     */
    ENGLISH("english"),
    /**
     * 中文
     */
    CHINESE("chinese"),
    ;
    private final String language;

    public static boolean isEn(String language) {
        return ENGLISH.language.equals(language);
    }
}
