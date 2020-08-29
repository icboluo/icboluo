package com.icboluo.tomcat.loginDemo.filter;


import com.icboluo.dataobject.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/day05login.jsp")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (null != loginUser) {
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/login.jsp").forward(request,resp);
        }
    }
}
