package com.icboluo.z2.web;


import com.icboluo.Constant;
import com.icboluo.z2.bean.User;
import com.icboluo.z2.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/pageQueryServlet")
public class PageQueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strPageNum = request.getParameter("pageNum");
        String strPageSize = request.getParameter("pageSize");

        int startCount = 0;
        int pageNum = 1;
        int pageSize = 3;
        //trim去空格
        if (strPageNum != null && !"".equals(strPageNum.trim())) {
            pageNum = Integer.parseInt(strPageNum);
        }
        if (strPageSize != null && !"".equals(strPageSize.trim())) {
            pageSize = Integer.parseInt(strPageSize);
        }
        startCount = (pageNum - 1) * pageSize;


        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList = userService.pageQuery(startCount, pageSize);

        int totalCount = userService.queryTotalCount();
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("list", userList);
        request.getRequestDispatcher(Constant.context_path + "/z2/list.jsp").forward(request, response);

    }
}
