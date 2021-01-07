package com.icboluo.designpattern.behavior.memorandum;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 备忘录
 * @author icboluo
 */
@AllArgsConstructor
@Data
public class Memento {
    /**
     * 攻击力
     */
    private int vit;

    private int def;

}
