package com.icboluo.z2.web;

import com.alibaba.fastjson.JSON;
import com.icboluo.z2.bean.User;
import com.icboluo.z2.service.UserService;
import com.icboluo.z2.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/queryAjaxListServlet")
public class QueryAjaxListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收
        //处理
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.queryAll();
        //响应
        String s = JSON.toJSONString(userList);
        request.setCharacterEncoding("utf-8");
        response.getWriter().print(s);

    }
}
