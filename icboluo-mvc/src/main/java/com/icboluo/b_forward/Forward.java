package com.icboluo.b_forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 转发：将一个servlet任务交给另一个servlet任务执行,是服务器内部转发，浏览器不可知
 *
 * @author icboluo
 */
@WebServlet(urlPatterns = "/forward")
public class Forward extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收数据
        String msg = request.getParameter("msg");
        // 处理数据
        request.setAttribute("info", "帮忙面试一下！");
        // dispatch调度，分配, 转发不需要全路径
        RequestDispatcher rd = request.getRequestDispatcher("/forwardTarget");
        rd.forward(request, response);
    }
}
