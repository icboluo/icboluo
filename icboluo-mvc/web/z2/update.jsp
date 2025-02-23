<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>"/>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="/updateUserServlet" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input value="${user.name}" type="text" class="form-control" id="name" name="name" readonly="readonly"
                   placeholder="请输入姓名"/>
            <input type="hidden" name="id" value="${user.id}">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio"
                   <c:if test="${user.sex=='男'}">checked</c:if> name="sex" value="男"/>男
            <input type="radio"
                   <c:if test="${user.sex=='女'}">checked</c:if> name="sex" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" value="${user.age}" class="form-control" id="age" name="age" placeholder="请输入年龄"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="广东" <c:if test="${user.address=='广东'}">selected</c:if>>广东</option>
                <option value="广西" <c:if test="${user.address=='广西'}">selected</c:if>>广西</option>
                <option value="湖南" <c:if test="${user.address=='湖南'}">selected</c:if>>湖南</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" value="${user.qq}" id="qq" class="form-control" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" value="${user.email}" id="email" class="form-control" name="email"
                   placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
