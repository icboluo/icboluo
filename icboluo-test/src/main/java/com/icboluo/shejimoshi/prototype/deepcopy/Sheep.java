package com.icboluo.shejimoshi.prototype.deepcopy;

import com.icboluo.util.IcBoLuoException;
import lombok.Data;
import lombok.ToString;

import java.io.*;

/**
 * @author icboluo
 * @date 2020/11/7 20:33
 */
@Data
@ToString
class Sheep implements Cloneable, Serializable {
    private String name;
    private int age;
    private String color;
    public Friend friend;

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    protected Object clone() {
        Sheep shee = null;
//        对基本数据类型进行copy
        try {
            Object deep = super.clone();
            //        转换成可操作类
            shee = (Sheep) deep;
//        将引用数据类型进行copy
            shee.friend = (Friend) friend.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shee;
    }

    /**
     * 用Serializable  将当前对象用序列化以流的方式转换，再反序列化回来
     * 本类以及本类的所有成员要实现可序列化
     *
     * @return
     */
    public Object deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IcBoLuoException();
        }
    }
}
