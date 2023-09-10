<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="../js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div align="center">
    <a
            href="${pageContext.request.contextPath}/pageQueryServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
    </a><br>
    <a
            href="${pageContext.request.contextPath}/z2/list.html" style="text-decoration:none;font-size:33px">list
    </a>
</div>
<h3><a href="${pageContext.request.contextPath}/z2/ajaxDemo01.html">ajax入门</a></h3>
<h3><a href="${pageContext.request.contextPath}/z2/ajaxDemo02.html">ajax和jquery</a></h3>
<h3><a href="${pageContext.request.contextPath}/z2/jsonDemo01.html">json</a></h3>
</body>
</html>
