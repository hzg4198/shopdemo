<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="${pageContext.request.contextPath}/admin/css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv">
    <form  action="${pageContext.request.contextPath}/LoginServlet" id="form">
<%--    <form  action="/shop/LoginServlet" id="form">--%>
        <h1 id="loginMsg">LOGIN IN</h1>
        <p>Username:<input id="username" name="username" type="text" value="${cookie.username.value }"></p>
        <p>Password:<input id="password" name="password" type="password"></p>
        <p>验证码：<input id="verify" name="verify" type="text"><img id="verifyPic" src="${pageContext.request.contextPath}/CheckCodeServlet"></p>
    <div class="checkbox-row">
        <p>
            <label class="checkbox-label">
                记住我:<input type="checkbox" class="form-control" name="remember" id="exampleInputPassword1" value="1">
            </label>
            <label class="checkbox-label">
                自动登录:<input type="checkbox" class="form-control2" name="remember2" id="exampleInputPassword2" value="1">
            </label>
        </p>
    </div>
        <span style="margin-left: 20px;color:#FF5765"><%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %></span>
        <span style="margin-left: 20px;color: rgb(173,216,230)">${param.msg}</span>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;
            <a href="${pageContext.request.contextPath}/admin/register.jsp">没有账号？点击注册</a>
        </div>
    </form>
</div>

</body>
<script type="text/javascript">
    var changeImg = document.getElementsByTagName("img")[0];
    changeImg.onclick=function (){
        this.src="/shop/" +
            "CheckCodeServlet?"+new Date().getMilliseconds()
    }
</script>
</html>