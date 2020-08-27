package com.icboluo.ee.tomcat.Day03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*响应协议：（）规定了服务器给浏览器发送数据的格式：行头体
行：协议、版本、状态
setStatus...200成功，404路径错误，500服务器端异常(bug)，302重定向，304从浏览器的缓冲中加载数据
操作响应头：response.setHeader(k,v)
refresh： 定时刷新； content-type：设置响应数据类型、编码
content-disposition：告诉浏览器以何种形式处理响应的资源
location配合302实现重定向：完成一次逻辑跳转，不能使用request域传递数据
 */
@WebServlet(urlPatterns = "/responseServlet01")
public class ResponseServlet01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // String username = request.getParameter("username");

        //response.setStatus(404);
        //response.setHeader("refresh","3;http://baidu.com");//进过3秒跳转页面

        response.setHeader("content-type","text/html;charset=utf-8");
        //response.setHeader("content-type","text/plain;charset=utf-8");//普通文本
        //response.setContentType("text/html;charset=utf-8");//简化方式
        response.getWriter().print("<h1>班长你好</h1>");
    }
}
