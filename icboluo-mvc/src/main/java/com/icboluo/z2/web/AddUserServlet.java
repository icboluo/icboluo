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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        var user = new User();

        try {
            BeanUtils.populate(user, parameterMap);
            UserServiceImpl userService = new UserServiceImpl();
            String qq = user.getQq();
            List<User> userList = userService.queryByQq(qq);
            if (null != userList && userList.size() > 0) {
                request.setAttribute("errorMsg", "qq号码已存在");
                request.getRequestDispatcher(Constant.context_path + "/z2/add.jsp").forward(request, response);
                return;
            }


            boolean addFlag = userService.saveUser(user);
            //响应数据
            if (addFlag) {
                response.sendRedirect("/queryAllServlet");
            } else {
                request.setAttribute("errorMsg", "新增数据失败");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("errorMsg", "新增数据严重失败");
            request.getRequestDispatcher(Constant.context_path + "/z2/error.jsp").forward(request, response);
        }
    }
}

