package com.icboluo.z2.web;

import com.alibaba.fastjson2.JSON;
import com.icboluo.z2.bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
    }
}
