package com.icboluo.tomcat.loginDemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //请求，post请求乱码
        HttpServletRequest request= (HttpServletRequest) req;
        String method = request.getMethod();
        if ("post".equalsIgnoreCase(method)) {
            req.setCharacterEncoding("utf-8");
        }
        //处理乱码
        HttpServletResponse response= (HttpServletResponse) resp;
        resp.setContentType("test/html;charset=utf-8");
        chain.doFilter(request, response);
    }
}
