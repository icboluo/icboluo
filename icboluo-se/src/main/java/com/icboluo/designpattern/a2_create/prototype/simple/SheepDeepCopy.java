package com.icboluo.designpattern.a2_create.prototype.simple;

import com.icboluo.util.IcBoLuoException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * 深拷贝
 *
 * @author icboluo
 * @since 2023-03-02 22:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class SheepDeepCopy implements Serializable, Cloneable {
    private String name;

    private SheepFriend friend;

    @Override
    protected SheepDeepCopy clone() throws CloneNotSupportedException {
        Object deep = super.clone();
        // 基本数据类型已经被拷贝
        SheepDeepCopy sheepDeepCopy = (SheepDeepCopy) deep;
        // 引用数据类型
        sheepDeepCopy.setFriend((SheepFriend) friend.clone());
        return sheepDeepCopy;
    }

    public SheepDeepCopy deepCopy() throws IOException {
        // 序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        // 反序列化
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        try (baos; oos; bais; ois) {
            return (SheepDeepCopy) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IcBoLuoException();
        }
    }
}
