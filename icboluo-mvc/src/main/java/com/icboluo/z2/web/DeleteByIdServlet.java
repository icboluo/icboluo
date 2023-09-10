package com.icboluo.z2.web;


import com.icboluo.Constant;
import com.icboluo.z2.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/deleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserServiceImpl userService = new UserServiceImpl();
        boolean deleteFlag = userService.deleteById(id);
        if (deleteFlag) {
/*            List<User> userList = userService.queryAll();
            request.setAttribute("list",userList);
            request.getRequestDispatcher("/list.jsp").forward(request,response);*/
            response.sendRedirect("/queryAllServlet");
        } else {
            request.setAttribute("errorMsg", "删除失败");
            request.getRequestDispatcher(Constant.context_path + "/z2/error.jsp").forward(request, response);
        }
    }
}
