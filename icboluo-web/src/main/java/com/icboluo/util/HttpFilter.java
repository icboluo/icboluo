package com.icboluo.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author icboluo
 * @date 2022-02-26 19:50
 */
//@Component
@Slf4j
//@WebFilter("/*")
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("in filter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
