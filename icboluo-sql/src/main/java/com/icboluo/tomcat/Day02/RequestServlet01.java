package com.icboluo.tomcat.Day02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP协议（HyperText Transfer Protocol，超文本传输协议）
 *作用：规定了浏览器和服务器之间传输数据的格式
 *HTTP1.0：发送一次请求，创建一个连接，获取一个网络资源，断开连接；
 *HTTP1.1：发送一次请求，创建一个连接，获取多个网络资源，断开连接；
 *特点：默认端口80，先请求在响应，成对出现
 *1. 请求报文：浏览器可以抓取请求的数据（用Fiddler），浏览器给服务器发送的请求数据的格式
 *2. 响应报文：服务器给客户端（浏览器）响应的报数据格式。
 *
 * 请求报文的组成：请求行，请求头，请求体；
 *   请求行：请求方式 url 协议/版本
 *   请求头：key：value
 *   请求体：提交请求数据
 *
 *get： 没有请求体，数据在url？后用&拼接
 *         密码会在url中出现，提交数据长度有限制
 *
*/
@WebServlet(urlPatterns = "/requestServlet01")
public class RequestServlet01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求行的请求方式
        String method = request.getMethod();
        System.out.println("method = " + method);

        //获取请求行的请求URL
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);

        //获取请求行的请求IP，当请求的ip为localhost的时候，获取的是IPv6的格式
        String remoteAddr = request.getRemoteAddr();//address
        System.out.println("remoteAddr = " + remoteAddr);

        //获取请求行的协议版本号
        String protocol = request.getProtocol();
        System.out.println("protocol = " + protocol);
        System.out.println("----------------------------------------------------------");
        String host = request.getHeader("host");
        String header = request.getHeader("user-agent");
        String referer = request.getHeader("referer");
    }
}

