Web（World Wide Web）即全球广域网，也称为万维网。
Javaweb: 用Java技术来解决相关web领域的技术总和（Web前端+Web后台）。
是用java技术开发web服务应用（软件）
两种软件架构模式:
B/S：Broswer / Server通过浏览器去访问网络资源
C/S：Client /Server打开特定的软件访问网络资源
B/S通信模式是B/S软件架构的通信模式，也就是浏览器和服务器之间数据交互的方式。B/S的请求和访问应该成对出现
B/S架构的优点：
1、跨平台：BS架构是基于网页语言的，与操作系统无关；
2、系统升级维护简单：只需要安装一个浏览器即可访问不同服务器的资源；

WEB资源：通过浏览器从网络中访问到的网络资源。分为：
1. 静态资源：指web页面中供人们浏览的数据始终是不变。比如：HTML、CSS、JS、图片、多媒体。
2. 动态资源：指web页面中供人们浏览的数据是由程序产生的，不同时
   间点访问web页面看到的内容各不相同。比如：JSP/Servlet、ASP、PHP
   URL （Uniform Resource Locator） ，（统一的资源定位器）统一资源定位符是对互联网上资源位置的
   一种表示，互联网上的每个文件都有一个唯一的URL。
   书写格式：协议://ip:端口/资源位置
   协议://ip:端口号/资源位置
   协议，http、https、ftp等
   域名，域名或IP地址，能够帮我们定位到互联网上的某一台服务器,域名先从本地找。没有去dos里面找,定位一台主机（服务器）
   端口号，端口号是一个应用程序在一台服务器上的编号。http协议的默认端：80
   资源位置，用于描述WEB资源在服务器上的位置
   参数=值，浏览器和服务器交互传递的数据。
   exa：https://www.baidu.com/s?ie=UTF-8&wd=java
   服务器，是提供计算服务的设备。由于服务器需要响应服务请求，并进行处理，
   因此一般来说服务器应具备承担服务并且保障服务的能力。
   服务器的构成包括处理器、硬盘、内存、系统总线等，和通用的计算机架构类似，但是由于需
   要提供高可靠的服务，因此在处理能力、稳定性、可靠性、安全性、可扩展性、可管理性等方面要求较高。
   在网络环境下，根据服务器提供的服务类型不同，分为文件服务器，数据库服务器，应用程序服务器，WEB服务器等。
   服务器构成：硬件软件
   服务器硬件：一台电脑主机，只不过这台电脑需要提供可靠的服务，因此在处理能力，稳定性，
   安全性方面要求更高。服务器只是一台设备，必须安装服务器软件才能提供服务。
   服务器软件本质上是一个应用程序（有代码编写而成），运行在服务器设备上。
   能够接收请求并根据请求给客户端响应数据，发布资源(静态和动态)。
   常见服务器：
1. Tomcat：Apache组织提供一个免费开源的小型的服务器软件。支持Servlet和JSP规范,性能高。开源，免费，性能高。
2. WebLogic：Bea公司的一个收费的大型的服务器软件，后被Oracle收购。支持EE的所有的规范
3. WebSphere：IBM公司的一个收费的大型的服务器软件，支持EE的所有的规范。（IBM：    阿里云：5000 ）
4. JBoss：是一个基于J2EE的开放源代码的应用服务器。JBoss是一个管理EJB的容器和服务器，JBoss核心服务不包括支持servlet/JSP的WEB容器，一般与Tomcat或Jetty绑定使用。

web项目：由web静态资源和动态资源组成；
web：存放web资源
静态文件（html，css，js）
WEB-INF
web.xml：web项目核心配置文件

项目发布：让tomcat运行我们的项目；
发布方式：
1、直接发布：将项目放到tomcat的webapp目录下，启动tomcat即可；
2、使用idea发布：
将tomcat配置到idea中
在idea中启动tomcat
注意事项：
使用IDEA发布项目时，url中不需要添加项目名和web目录名。只需要一个"/"即可。  "/" 等价于 "/项目名/web"

	概念：Servlet 是一个 JavaWeb 开发中的一个小程序。客户端向服务器发送请求，由Servlet进行数据业务逻辑的处理，将处理的结果，响应回客户端。


## HTTP 协议 （HyperText Transfer Protocol，超文本传输协议）

作用：规定了浏览器和服务器之间传输数据的格式
HTTP1.0：发送一次请求，创建一个连接，获取一个网络资源，断开连接；
HTTP1.1：发送一次请求，创建一个连接，获取多个网络资源，断开连接；
特点：默认端口80，先请求在响应，成对出现
1. 请求报文：浏览器可以抓取请求的数据（用Fiddler），浏览器给服务器发送的请求数据的格式
2. 响应报文：服务器给客户端（浏览器）响应的报数据格式。
   请求报文的组成：请求行，请求头，请求体；
   请求行：请求方式 url 协议/版本
   请求头：key：value
   请求体：提交请求数据
   get： 没有请求体，数据在url？后用&拼接
   密码会在url中出现，提交数据长度有限制

## jsp

jsp:Java Server Pages可以写java&html
第一次访问JSP页面： JSP页面被tomcat翻译成Servlet

jspService方法：
jsp中的html：out.print（"<html>"）
jsp中的java部分：原样输出

jsp中写java：
脚本片段<%%>
脚本表达式：<%=%>代替response.getWriter().print();
EL:expression language,${}代替脚本表达式，主要取值(从域对象)并显示
jsp的域对象：page、reques、session、servletContext
ctrl alt shift u 框架图
jstl： the javaserver pages standard tag library 标准标签库
用标签封装了一些通用的业务逻辑
c:if,c:foreach就是这里面的

## ajax

Ajax：Asynchronous Javascript And XML（异步 JavaScript 和 XML）
js原生Ajax的开发步骤
1. 创建Ajax引擎对象--XMLHttpRequest对象；
2.  引擎）；
3. 绑定提交地址；
4. 发送请求；
5. 服务器接收请求并响应数据（文本或者xml数据）;
6. 接收响应数据；

 ## tomcat

tomcat 需要10以上才可以支持jakarta

tomcat项目还是要web文件夹有一个蓝色的圆圈标记，这样才不会idea各种打包异常 
