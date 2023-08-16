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
    <%--action="${pageContext.request.contextPath }/admin/home.jsp"--%>
    <form  action="/shop/LoginServlet" id="form">
        <h1 id="loginMsg">LOGIN IN</h1>
        <p>Username:<input id="username" name="username" type="text"></p>
        <p>Password:<input id="password" name="password" type="password"></p>
        <p>验证码：<input id="verify" name="verify" type="text"><img src="/shop/CheckCodeServlet"></p>
        <span><%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %></span>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="register.html">没有账号？点击注册</a>
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