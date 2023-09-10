package com.icboluo.z2.web;

import com.icboluo.Constant;
import com.icboluo.z2.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import com.icboluo.z2.bean.User;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        //beanutils：apache提供的工具类，用来把map中的数据封装到javabean中
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            UserServiceImpl userService = new UserServiceImpl();
            boolean updateFlog = userService.updateUser(user);
            if (updateFlog) {
                response.sendRedirect("/queryAllServlet");
            } else {
                request.setAttribute("errorMsg", "更新失败");
                request.getRequestDispatcher(Constant.context_path + "/z2/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("errorMsg", "更新严重失败");
            request.getRequestDispatcher(Constant.context_path + "/z2/error.jsp").forward(request, response);
        }
    }
}
