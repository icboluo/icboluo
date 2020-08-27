package com.icboluo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icboluo.dataobject.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * json 转换为对象 ：
 * Student student = JSON.parseObject(jsonObject.toString(), Student.class);
 * String to java obj:
 * IcGeneralCO icGeneralCO = JSON.parseObject(s, IcGeneralCO.class);
 *
 * @author lp
 */

public class JsonTest {

    @Test
    public void test1() {
        String s = "{'data':'1','name':'1'}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        //ClassCastException
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println("data = " + data);

    }

    @Test
    public void test3() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        students.add(new Student());
        String s = JSON.toJSONString(students);
        List<Student> students1 = JSONArray.parseArray(s, Student.class);
        System.out.println("students1 = " + students1);
        Student student = new Student();
    }

    @Test
    public void test4() {
        Student student = new Student();
        String s = JSON.toJSONString(student);
        Student student1 = JSONObject.parseObject(s, student.getClass());
        System.out.println("student1 = " + student1);
    }

}
