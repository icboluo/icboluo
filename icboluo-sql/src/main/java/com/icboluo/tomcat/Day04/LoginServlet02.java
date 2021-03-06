package com.icboluo.tomcat.Day04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet02")
public class LoginServlet02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remeber = request.getParameter("remeber");
        if ("remeberMe".equals(remeber)) {
            Cookie usernameCookie = new Cookie("username", "username");
            Cookie passwordCookie = new Cookie("password", "password");

            //设置cookie max存活时间
            usernameCookie.setMaxAge(60 * 60);
            passwordCookie.setMaxAge(60 * 60);

            //设置有效路径
            usernameCookie.setPath("/usernameTemp");
            passwordCookie.setPath("/passwordTemp");

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

        } else {
            //清除cookie,就是覆盖name&value
            Cookie usernameCookie = new Cookie("username", "");
            Cookie passwordCookie = new Cookie("password", "");

            usernameCookie.setMaxAge(0);
            passwordCookie.setMaxAge(0);

            usernameCookie.setPath("/usernameTemp");
            passwordCookie.setPath("/passwordTemp");

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
        }
    }
}
