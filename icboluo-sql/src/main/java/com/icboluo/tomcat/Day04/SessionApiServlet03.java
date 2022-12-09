package com.icboluo.tomcat.Day04;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/sessionApiServlet03")
public class SessionApiServlet03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //（第一次）创建或（第二次以后）获取session
        HttpSession session = request.getSession();

        //域对象操作取值存值移除值
        session.setAttribute("username", "班长");
        String username = (String) session.getAttribute("username");
        //session.removeAttribute("username");
        System.out.println(username);
        //获取session的id
        System.out.println(session.getId());
        System.out.println(request.getRemoteAddr());



    }
}
