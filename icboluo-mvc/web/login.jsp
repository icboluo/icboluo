<%@ page contentType="text/html;charset=UTF-8" %>
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
