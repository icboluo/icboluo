package com.icboluo.file;

import com.icboluo.common.AbstractFilePathConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2020-08-12 15:32
 */
class SerializeDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student stu = new Student(18, "小明");
//       TODO 这里会乱码应该怎么处理呢
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AbstractFilePathConstant.A));
        oos.writeObject(stu);
        oos.close();
//        字节输入流，读取对象（已经读取到了）
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AbstractFilePathConstant.A));
        Student student = (Student) ois.readObject();
        ois.close();
        System.out.println("student = " + student);
        List<Student> list = new ArrayList<>();
        list.add(stu);
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(AbstractFilePathConstant.A));
        oos2.writeObject(list);
        oos2.close();
    }
}
