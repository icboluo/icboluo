package com.icboluo.file;

import com.icboluo.constant.FileRelativePathPre;
import com.icboluo.object.StatusStudent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2020-08-12 15:32
 */
class SerializeDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StatusStudent stu = new StatusStudent(18, "小明");
        // 对象流 TODO 这里会乱码应该怎么处理呢
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt"));
        oos.writeObject(stu);
        oos.close();

        // 字节输入流，读取对象（已经读取到了）
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt"));
        StatusStudent student = (StatusStudent) ois.readObject();
        System.out.println("student = " + student);
        ois.close();

        List<StatusStudent> list = new ArrayList<>();
        list.add(stu);
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(FileRelativePathPre.SE + FileRelativePathPre.RESOURCES + "a.txt"));
        oos2.writeObject(list);
        oos2.close();
    }
}
