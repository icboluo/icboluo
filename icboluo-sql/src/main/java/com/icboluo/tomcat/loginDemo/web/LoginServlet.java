package com.icboluo.tomcat.loginDemo.web;


import com.icboluo.dataobject.User;
import com.icboluo.tomcat.loginDemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取验证码
        String inputCheckCode = request.getParameter("checkCode");
        String serverCode = (String) request.getSession().getAttribute("code");
        if (!serverCode.equalsIgnoreCase(inputCheckCode)) {
            response.sendRedirect("day02login.html");
        }
        //封装数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserService userService = new UserService();
        User loginUser = userService.login(user);


        if (loginUser == null) {
            response.setContentType("text/html;charset=utf-8");
            //登录失败在原界面跳出警告字样:方案2：copy html加一句java
            String msg = "用户名或密码错误";
            //跳转：转发，重定向
            request.setAttribute("errorMsg", msg);
            request.getRequestDispatcher("/day05login.jsp").forward(request, response);
        } else {
            //记住账号和密码功能
            Cookie nameCookie = new Cookie("username", "张三");
            Cookie passwordCookie = new Cookie("password", "123");
            nameCookie.setMaxAge(60 * 60);
            passwordCookie.setMaxAge(60 * 60);
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
            // 登录成功之后将数据储存到session中
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            request.getRequestDispatcher("/day05login.jsp").forward(request, response);
        }
    }
}
