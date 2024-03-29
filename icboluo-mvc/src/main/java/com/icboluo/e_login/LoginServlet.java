package com.icboluo.e_login;


import com.icboluo.Constant;
import com.icboluo.util.response.SingleResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @author icboluo
 */
@WebServlet(urlPatterns = "/login")
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
        // 获取验证码
//        String inputCheckCode = request.getParameter("checkCode");
//        String serverCode = (String) request.getSession().getAttribute("code");
//        if (!serverCode.equalsIgnoreCase(inputCheckCode)) {
//            response.sendRedirect("login.jsp");
//        }
        LoginMapper userService = new LoginMapper();
        SingleResponse login = userService.queryByUsernameAndPassword(username, password);


        if (!login.isSuccessful()) {
            response.setContentType("text/html;charset=utf-8");
            //登录失败在原界面跳出警告字样:方案2：copy html加一句java
            request.setAttribute("errorMsg", "用户名或密码错误");
            // 转发不需要使用全路径
            request.getRequestDispatcher("/login.jsp").forward(request, response);
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
            session.setAttribute("loginUser", username);
            response.setStatus(302);
            // 重定向需要使用全路径
            response.setHeader("location", Constant.context_path + "/loginSuccess.jsp");
        }
    }
}
