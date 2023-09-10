package com.icboluo.z2.web;

import com.alibaba.fastjson.JSON;
import com.icboluo.z2.service.UserService;
import com.icboluo.z2.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/checkedNameServlet")
public class CheckedNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //接收
        String userName = request.getParameter("name");
        //处理
        UserService userService = new UserServiceImpl();
        boolean checkFlag=userService.queryByUserName(userName);
        //响应
        HashMap<String, Object> result = new HashMap<>();
        result.put("checkFlag", checkFlag);
        String s = JSON.toJSONString(result);

        response.getWriter().print(s);
    }
}
