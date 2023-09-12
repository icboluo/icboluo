<%--
  Created by IntelliJ IDEA.
  User: icboluo
  Date: 2023/9/9
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit" name="login"><br>
</form>
${errorMsg}
<form action="${pageContext.request.contextPath}/register" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit" name="注册"><br>
</form>
</body>
</html>
