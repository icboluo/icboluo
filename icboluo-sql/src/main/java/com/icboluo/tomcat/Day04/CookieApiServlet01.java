package com.icboluo.tomcat.Day04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
会话技术：为完成一个网络上的活动，用户打开浏览器多次请求服务器的资源，然后关闭浏览器。整个过程称之为一个会话；
会话就是由多次请求和响应组成的一次网络上的活动；
cookie 饼干，小甜点，小数据，数据保存到浏览器
session （会议）数据保存到服务器，为每个客户端创建一个容器
 */
@WebServlet(urlPatterns = "/cookieApiServlet")
public class CookieApiServlet01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie c= new Cookie("username", "班长");
        String name = c.getName();
        String value = c.getValue();
        c.setValue("张三");

        //设置cookie的有效路径
        c.setPath("/demo07");
        //发送cookie到浏览器
        response.addCookie(c);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName()+"==="+cookie.getValue());
        }
    }
}
