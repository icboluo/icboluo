package com.icboluo.z2.web;

import com.alibaba.fastjson.JSON;
import com.icboluo.z2.bean.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.icboluo.z2.bean.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/ajaxDemoServlet")
public class AjaxDemoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收请求数据
        String name = request.getParameter("name");
        String age = request.getParameter("age");


        //处理数据
        System.out.println("请求进来了");
        //int i=1/0;
        //响应数据
        response.getWriter().print("hello");
        /*xml响应一个对象数据
         * <user>
         *     <id> 1</id>
         *     <name> 班长</name>
         * </user>
         * json是一个严格的js对象exa：
         * js：id：1
         * json："id"：1
         * json响应一个对象数据:(json还有其他写法)
         * 下面这个写法太难受，导包用转换工具转换*/
        response.getWriter().print("{\"id\":1,\"name\":\"班长\"}");

        User user = new User();
        user.setId(1);
        user.setName("小花");

        String s = JSON.toJSONString(user);
        response.getWriter().print(s);

        List<Student> stuList = new ArrayList<>();
        Student stu = new Student();
        stu.setId(1);
        stu.setStudentName("张弛");
        stu.setStudentAge(18);

        Student stu1 = new Student();
        stu1.setId(2);
        stu1.setStudentName("斌宝宝");
        stu1.setStudentAge(18);

        stuList.add(stu);
        stuList.add(stu1);


        String s7 = JSON.toJSONString(stuList);
        System.out.println(s7);
    }

    //3、把map转换成JSON复杂数据格式：
    //map==>{"no":1,"city":"上海","stu":{"id":1,"studentAge":18,"studentName":"张弛"}}
    public void testComplexObjToJson() {

        Map<String, Object> map = new HashMap<>();

        map.put("no", 1);
        map.put("city", "上海");

        Student stu = new Student();
        stu.setId(1);
        stu.setStudentName("张弛");
        stu.setStudentAge(18);
        map.put("stu", stu);

        String s = JSON.toJSONString(map);
        System.out.println(s);
    }

    public void testFuZa2() {
        Student stu = new Student();
        stu.setId(1);
        stu.setStudentName("张弛");
        stu.setStudentAge(18);

/*        Course course = new Course();
        course.setId(1001);
        course.setCode("1001");
        course.setCourseName("Java");

        stu.setCourse(course);*/

        String s = JSON.toJSONString(stu);
    }
}
