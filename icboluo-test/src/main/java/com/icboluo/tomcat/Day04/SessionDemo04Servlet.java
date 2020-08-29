package com.icboluo.tomcat.Day04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/*会话级别：浏览器关闭后session持久化不够，修改age可以解决
* session的生命周期：第一次request.getsession、setAttribute、session.invalidate/after30min(可修改)/非正
* 常关闭(正常关闭在tomcat的bin中有一个shutdown命令，会自动创建一个文件，目录可在日志中找到session.ser)
* servlet域：
* reqest一次请求一次响应
* session多次请求多次响应
* servletContext整个项目，tomcat启动期间整个项目共享
* */
@WebServlet(urlPatterns = "/sessionDemo04Servlet")
public class SessionDemo04Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = session.getId();
        //手动销毁，每次回出现不同的id
        session.invalidate();

        Cookie jsessionid = new Cookie("JSESSIONID", id);
        jsessionid.setMaxAge(60*60);//1小时
        response.addCookie(jsessionid);

    }
}
