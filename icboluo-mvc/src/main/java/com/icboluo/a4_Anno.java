package com.icboluo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 注意：url中的 / 非常重要，不能忘记加
 * 有一个专门解决http协议的HttpServlet extends GenericServlet
 * 需要extends HttpServlet
 * HttpServlet类也是一个抽象类，为什么没有像GenericServlet一样，将service方法设计成抽象方法供用户扩展呢？
 * 1、HttpServlet中的service方法统一接收所有的客户端发送的HTTP请求；
 * 2、HTTP请求又可分为：GET,POST等请求，service方法在接收到请求后根据不同的请求方式，调用对应的方法来处理请求，如：doGet处理的是get请求，doPost处理的是post请求；
 * 3、开发人员根据客户端发送的不同请求方式，重写不同的方法来处理请求即可；
 * <p>
 * class经过web.xml生产url，
 * html加载url产生网页，输入html就是输入url
 * 网页中输入...html
 * 1. 完全匹配          /user/hello          资源路径为/user/hello时可以访问
 * 2. 目录匹配         /user/*              资源路径中含有/user目录均可访问
 * 3. 后缀名匹配        *.do                 资源路径中以.do结尾的均可访问
 * 4. 缺省路径         /                    访问的路径找不到，就会去找缺省路径
 *
 * @author icboluo
 * @since 2023-09-09 20:07
 */
@Slf4j
@WebServlet("/annotation")
public class a4_Anno extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("make HttpServlet and annotation optimize Servlet");
        resp.getWriter().print("make HttpServlet and annotation optimize Servlet");
    }
}
