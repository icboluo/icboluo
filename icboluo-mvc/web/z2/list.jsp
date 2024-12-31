<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jstl属于jsp，目前已经过时，不再使用--%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="z2/add.jsp">添加联系人</a></td>
        </tr>
        <tr class="success">
            <th>计数</th>
            <th>id</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <%--items要遍历的对象--%>
        <c:forEach items="${list}" var="user" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.sex}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                    <%--空链接href="#"/"javascript:;"/"javascript:void(0);"--%>
                <td>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/queryByIdServlet?id=${user.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" onclick="delUserById(${user.id})" href="javascript:;">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8">
                <div class="form-inline">
                    <c:if test="${pageNum>1}">
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/pageQueryServlet?pageNum=${pageNum==1?1:pageNum-1}&pageSize=${pageSize}">上一页</a>
                    </c:if>
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <c:if test="${pageNum==i}">
                            <a class="btn btn-success btn btn-default"
                               href="${pageContext.request.contextPath}/pageQueryServlet?pageNum=${i}&pageSize=${pageSize}">${i}</a>
                        </c:if>
                        <c:if test="${pageNum!=i}">
                            <a class="btn btn-default" href="${pageContext.request.contextPath}/pageQueryServlet?pageNum=${i}&pageSize=${pageSize}">${i}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageNum<totalPage}">
                        <a class="btn btn-default"
                           href="${pageContext.request.contextPath}/pageQueryServlet?pageNum=${pageNum==totalPage?totalPage:pageNum+1}&pageSize=${pageSize}">下一页</a>
                    </c:if>
                    <select class="form-control" name="pageSize" id="pageSize">
                        <option <c:if test="${pageSize==2}">selected</c:if> value="2">2条/页</option>
                        <option <c:if test="${pageSize==3}">selected</c:if> value="3">3条/页</option>
                        <option <c:if test="${pageSize==4}">selected</c:if> value="4">4条/页</option>
                    </select>
                    总页数：${totalPage}
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
<script>
    function delUserById(id) {
        const confirm = window.confirm("确认要删除？");
        /*console.log(confirm);控制台打印*/
        if (confirm) {
            location.href = "${pageContext.request.contextPath}/deleteByIdServlet?id=" + id
        }
    }

    $("#pageSize").change(function () {
        const pageSize = this.value;
        /*        var pageSize = $("#pageSize").val();*/
        location.href = "${pageContext.request.contextPath}/pageQueryServlet?pageNum=1&pageSize=" + pageSize;
    });
</script>
</html>
