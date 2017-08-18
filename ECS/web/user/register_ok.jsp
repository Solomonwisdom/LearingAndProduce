<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>用户注册 - 西电购书系统</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
		<link rel="stylesheet" href="http://static.runoob.com/assets/foundation-icons/foundation-icons.css">
		<script src="https://cdn.static.runoob.com/libs/foundation/5.5.3/js/vendor/modernizr.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" src="../js/vendor/jquery.js"></script>
		<style>
			input{vertical-align: middle; font-size:14px}
			body{font-family: "宋体", Arial, Helvetica, sans-serif; margin:auto; font-size:12px; background:#fff; color:#414141}
			.login_step{ height:30px; font-size:18px; text-align:center; padding-top:10px;  padding-bottom: 10px;}
			.red_bold{ color:#ff0000; font-weight:bold}
			.bg {  min-width: 1000px;  max-width: 100%;  margin: 0px auto 0;  background: url("../images/bg.jpg") repeat #ffffff;  height: 75%;  padding: 10px 0 0 0;  }
			.gap {  margin-top: 10px; margin-bottom:10px; padding: auto;}
			.text_left span {font-size: 12px}
		</style>
	</head>
	<body>
	<div class="bg">
		<div class="row text-center">
			<div class="login_step">
				注册步骤: 1.填写信息 > 2.验证邮箱 >
				<span class="red_bold">3.注册成功</span>
			</div>
		</div>
		<div class="row gap">
			<div style="margin-left:10%;margin-right:15%; padding-top: 10px;">
				<h5>${user.nickname},欢迎加入西电购书系统!</h5>
				<h6>请牢记您的登录邮件地址：${user.email}%></h6>
				<p><a href="${pageContext.request.contextPath}/user/login_form.jsp">您可以去登录了。</a> </p>
			</div>
		</div>
	</div>
	<%@include file="../common/footForUser.jsp"%>
	</body>
</html>

