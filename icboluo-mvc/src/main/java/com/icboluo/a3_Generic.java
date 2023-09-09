package com.icboluo;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * servlet的优化:实现接口的过程中，用到的重写方法只有service，直接继承 GenericServlet即可
 *
 * @author icboluo
 * @since 2023-09-09 20:03
 */
@Slf4j
public class a3_Generic extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        log.warn("make GenericServlet optimize Servlet");
        servletResponse.getWriter().print("make GenericServlet optimize Servlet");
    }
}
