package com.icboluo.z2.web;


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

@WebServlet(urlPatterns = "/queryAllServlet")
public class QueryAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserServiceImpl();
        List<User> userList = userService.queryAll();

        /*
        request.getRequestDispatcher:获取请求转发器
         */
        request.setAttribute("list", userList);
        request.getRequestDispatcher("/z2/list.jsp").forward(request, response);
    }
}
