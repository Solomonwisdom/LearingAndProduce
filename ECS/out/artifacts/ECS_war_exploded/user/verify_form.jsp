<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>用户注册 - 西电购书系统</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
		<link rel="stylesheet" href="http://static.runoob.com/assets/foundation-icons/foundation-icons.css">
		<script src="../js/vendor/jquery.js" type="text/javascript"></script>
		<script src="https://cdn.static.runoob.com/libs/foundation/5.5.3/js/vendor/modernizr.js"></script>
		<style>
			input{vertical-align: middle; font-size:14px}
			body{font-family: "宋体", Arial, Helvetica, sans-serif; margin:auto; font-size:12px; background:#fff; color:#414141}
			.login_step{ height:30px; font-size:18px; text-align:center; padding-top:10px;  padding-bottom: 10px;}
			.red_bold{ color:#ff0000; font-weight:bold}
			.contain { margin-left:15%;margin-right:15%; padding-top: 10px; }
			.bg {  min-width: 1000px;  max-width: 100%;  margin: 0px auto 0;  background: url("../images/bg.jpg") repeat #ffffff;  height: 75%;  padding: 10px 0 0 0;  }
			.gap {  margin-top: 10px; margin-bottom:10px; padding: auto;}
			.look_email{ height:190px; border-top:solid 1px #bfbfbf; padding:40px 0 0 140px; font-size:14px;}
			.text_left span {font-size: 12px}
		</style>
	</head>
	<body>
	<%@include file="../common/headForUser.jsp"%>
	<div class="bg">
		<div class="row text-center">
			<div class="login_step">
				注册步骤: 1.填写信息 >
				<span class="red_bold">2.验证邮箱</span> > 3.注册成功
			</div>
		</div>
		<div class="row gap">
			<form action="${pageContext.request.contextPath}/user/checkucode.action?email=${user.email}" method="post" id="f">
				<div style="margin-left:10%;margin-right:15%; padding-top: 10px;">
					<h5>
						感谢您注册西电购书系统！现在请按以下步骤完成您的注册!
					</h5>
					<div class="look_email">
						<h6 class="gap">
							第一步：查看您的电子邮箱
						</h6>
						<div class="row">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们给您发送了验证邮件，邮件地址为：
							<span class="red"><span id="lblEmail">${user.email }</span>
                    </span>
							<span>
						请登录${user.nickname }的邮箱收信。
                    </span>
						</div>
						<h6 class="gap">
							第二步：输入验证码${user.emailVerifyCode }
						</h6>
						<div class="row">
							<div style="padding-left: 20px">
								<label for="validatecode">输入您收到邮件中的验证码：
									<input name="code" type="text" id="validatecode" class="yzm_text" />
								</label>
							</div>
						</div>
						<div class="row text-center gap">
							<input class="button tiny" type="submit" value="完     成" id="Button1" style="font-size: 16px" />
							<span id="errorMsg" class="right red_bold">${code_error}</span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@include file="../common/footForUser.jsp"%>
	<script>
        $(document).ready(function() {
            $(document).foundation();
        })
	</script>
	</body>
</html>

