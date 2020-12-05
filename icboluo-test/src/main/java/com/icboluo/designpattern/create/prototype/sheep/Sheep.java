package com.icboluo.designpattern.create.prototype.sheep;

import com.icboluo.util.IcBoLuoException;
import lombok.Data;
import lombok.ToString;

/**
 * @author icboluo
 * @date 2020/11/7 20:33
 */
@Data
@ToString
class Sheep implements Cloneable {
    private String name;
    private int age;
    private String color;
    public Sheep friend;

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IcBoLuoException();
        }
    }
}
