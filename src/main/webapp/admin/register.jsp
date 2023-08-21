<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>欢迎注册</title>
  <link href="${pageContext.request.contextPath}/admin/css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
  <div class="reg-content">
    <h1>欢迎注册</h1>
    <span>已有帐号？</span> <a href="index.jsp">登录</a>
  </div>
  <form id="reg-form" action="${pageContext.request.contextPath}/RegisterServlet" method="get">
    <table>
      <tr>
        <td>用户名</td>
        <td class="inputs">
          <input name="username" type="text" id="username">
          <br>
          <span id="username-status"></span>
<%--          <span id="username_err" class="err_msg" style="display: none">用户名不太受欢迎</span>--%>
        </td>
      </tr>
      <tr>
        <td>密码</td>
        <td class="inputs">
          <input name="password" type="password" id="password">
          <br>
          <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
        </td>
      </tr>
      <tr>
        <td>验证码</td>
        <td class="inputs">
          <input name="verify" type="text" id="verify"><img src="${pageContext.request.contextPath}/CheckCodeServlet" id="verifyPic" style="display: inline-flex">
          <br>
          <span id="verify_err" class="err_msg"><%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %></span>
        </td>
      </tr>
    </table>
    <div class="buttons">
      <input value="注 册" type="submit" id="reg_btn">
    </div>
    <br class="clear">
  </form>

</div>
</body>
<script src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"> </script>
<script>
  $("#verifyPic").click(function(){
    $(this).attr("src", "${pageContext.request.contextPath}/CheckCodeServlet?"+new Date().getMilliseconds())
  })
  $("#username").on("blur",function (){

    var username = $(this).val();
    var spanElement = $("#username-status");
    $.ajax({
      url:"${pageContext.request.contextPath}/CheckUsernameServlet",
      type:"GET",
      data:{username:username},
      success:function (response) {
        if(response.available){
            spanElement.text("用户名可用").css("color","green");
        }else {
            spanElement.text("用户名被占用").css("color","red");
        }
      },
      error:function (){
          spanElement.text("请求失败").css("color","red");
      }
    });
    if(username === ""||username === 'null'){
        spanElement.hide();
    }else {
        spanElement.show();
    }
  })
</script>
</html>