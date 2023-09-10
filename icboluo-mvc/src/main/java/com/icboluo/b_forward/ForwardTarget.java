package com.icboluo.b_forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author icboluo
 */
@WebServlet(urlPatterns = "/forwardTarget")
public class ForwardTarget extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String info = (String) request.getAttribute("info");
        System.out.println(info);
        System.out.println("面试中.................................................");
        response.getWriter().print("this people is ok");
    }
}
