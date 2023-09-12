package com.icboluo.e_login;


import com.icboluo.util.response.SingleResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author icboluo
 */
@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginMapper userService = new LoginMapper();
        SingleResponse login = userService.register(username, password);
        response.setContentType("text/html;charset=utf-8");
        if (login.isSuccessful()) {
            request.setAttribute("errorMsg", username + " 注册成功");
        } else {
            request.setAttribute("errorMsg", username + " 注册失败");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
