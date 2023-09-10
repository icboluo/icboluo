package com.icboluo.e_login;


import com.icboluo.Constant;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author icboluo
 */
@WebFilter(urlPatterns = "/loginSuccess.jsp")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (null != loginUser) {
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher(Constant.context_path + "/login.jsp").forward(request, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
