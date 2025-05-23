<%@ page contentType="text/html;charset=UTF-8" %>
<!-- HTML5文档-->
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
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="../js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container text-left">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="get" class="form-horizontal">
        ${errorMsg}
        <div class="form-group">
            <label class="col-lg-2 control-label" for="name">姓名：</label>
            <div class="col-lg-8">
                <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
            </div>
            <label class="col-lg-2 control-label" id="nameMsg"></label>
        </div>

        <div class="form-group">
            <label class="col-lg-2 control-label">性别：</label>
            <div class="col-lg-3">
                <input type="radio" name="sex" value="男" checked="checked"/>男
            </div>
            <div class="col-lg-3">
                <input type="radio" name="sex" value="女"/>女
            </div>
            <label class="col-lg-2 control-label" id="sexMsg"></label>
        </div>

        <div class="form-group">
            <label for="age" class="control-label col-lg-2">年龄：</label>
            <div class="col-lg-8">
                <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
            </div>
        </div>

        <div class="form-group">
            <label for="jiguan" class="col-lg-2 control-label">籍贯：</label>
            <div class="col-lg-8">
                <select name="address" class="form-control" id="jiguan">
                    <option value="广东">广东</option>
                    <option value="广西">广西</option>
                    <option value="湖南">湖南</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="qq" class="control-label col-lg-2">QQ：</label>
            <div class="col-lg-8">
                <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号码"/>
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-lg-2 control-label">Email：</label>
            <div class="col-lg-8">
                <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
            </div>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
<script>
    $("#name").blur(function () {
        //发送请求
        $.get("/checkedNameServlet", {name: this.value}, function (result) {
            //处理响应数据
            var checkFlag = result.checkFlag;
            if (checkFlag) {
                $("#nameMsg").html("<span style='color:red'>用户名已存在</span>")
            }else {
                $("#nameMsg").html("<span style='color:green'>√</span>");
            }
        },"json");

    });
</script>
</html>
