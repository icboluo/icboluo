package com.icboluo.b_redirect;

import com.icboluo.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 重定向，当浏览器请求一个URL的时候，服务器返回一个重定向指令，告诉服务器地址已经改变，需要使用新的URL再重新发送请求
 *
 * @author icboluo
 */
@WebServlet(urlPatterns = "/redirect")
public class Redirect extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String msg = request.getParameter("msg");
        System.out.println(msg);

        response.setStatus(302);
        // 注意此块是端口号后面全路径
        response.setHeader("location", Constant.context_path + "/redirectTarget");
    }
}
