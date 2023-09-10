package com.icboluo;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

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
        // 解决post请求中文数据乱码问题
        if ("post".equalsIgnoreCase(method)) {
            request.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {

    }
}
