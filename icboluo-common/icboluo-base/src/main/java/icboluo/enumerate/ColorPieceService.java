package icboluo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

/**
 * @author icboluo
 * @date 2021-06-19 21:06
 */
public class ColorPieceService implements EnumServiceInterface {

    @Override
    public EnumSet allSet() {
        return EnumSet.allOf(ColorEnum.class);
    }

    @AllArgsConstructor
    @Getter
    public enum ColorEnum implements EnumInter {
        /**
         * 枚举项
         */
        A("1", "en1", "zh2"),
        B("2", "en2", "zh3"),
        C("3", "en3", "zh1"),
        D("4", "en4", "zh4"),
        ;
        private final String id;
        private final String en;
        private final String zh;
    }
}
