package com.icboluo.z2.web;


import com.icboluo.Constant;
import com.icboluo.z2.bean.User;
import com.icboluo.z2.service.UserService;
import com.icboluo.z2.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/queryByIdServlet")
public class QueryByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        UserService userService = new UserServiceImpl();
        User user=userService.queryById(id);

        request.setAttribute("user",user);
        request.getRequestDispatcher(Constant.context_path + "/z2/update.jsp").forward(request,response);
    }
}
