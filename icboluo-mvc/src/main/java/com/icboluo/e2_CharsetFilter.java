package com.icboluo;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/response", "/responseBodyServlet", "/xyz"})
public class e2_CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //请求，post请求乱码
        HttpServletRequest request = (HttpServletRequest) req;
        String method = request.getMethod();
        if ("post".equalsIgnoreCase(method)) {
            req.setCharacterEncoding("utf-8");
        }
        //处理乱码
        HttpServletResponse response = (HttpServletResponse) resp;
        resp.setContentType("test/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
