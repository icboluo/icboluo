package com.icboluo.se.file;

import com.icboluo.common.AbstractFilePathConstant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author icboluo
 * @date 2020-08-12 14:59
 */
public class Demo09 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AbstractFilePathConstant.A));
        Object o = ois.readObject();
    }

}
