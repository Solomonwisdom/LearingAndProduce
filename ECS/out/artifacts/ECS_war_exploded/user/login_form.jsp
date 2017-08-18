<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>登录</title>

	<link rel="stylesheet" href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
	<link rel="stylesheet" href="http://static.runoob.com/assets/foundation-icons/foundation-icons.css">
	<script src="https://cdn.static.runoob.com/libs/foundation/5.5.3/js/vendor/modernizr.js"></script>
	<!--<link ref="stylesheet" href="../css/foundation.min.css" type="text/css"/>-->
	<!--<link ref="stylesheet" href="../css/foundation-icons.css" type="text/css"/>-->
	<!--<script src="../js/vendor/foundation.min.js" type="text/javascript"></script>-->
	<script src="../js/vendor/jquery.js" type="text/javascript"></script>
	<style>
		input{vertical-align: middle; font-size:14px}
		body{font-family: "宋体", Arial, Helvetica, sans-serif; margin:auto; font-size:12px; background:#fff; color:#414141}
		.contain { margin-left:12%;margin-right:12%; padding-top: 10px; }
		.logo { border-bottom: solid 3px #f27b21;padding-left: 5%; }
		.bg {  min-width: 1000px;  max-width: 100%;  margin: 0px auto 0;  background-color: #a8cfbf;  height: 480px;  padding: 40px 0 0 0;  }
		.tiaomu {  font-size: 16px;  background: none;  color: #000; }
		.bj_none {  font-size: 14px; background:none;  padding-bottom:15px;  color:#1a1a1a; text-decoration: none }
		.introduce {  margin-top:65px;  float:left;  padding-left: 40px; }
		.introduce img { margin-bottom:10px;}
		.introduce ul { list-style:none;line-height:18px; border-top:solid 1px #5b5a5a; padding:10px 0 0 2px}
		.introduce ul li { background:url(../images/bj_d_black.gif) no-repeat left 7px; padding-left:12px}
		.login {  float: right;  background: url(../images/logonbg.png) no-repeat; padding-right: 120px;}
		.main { margin-left:65px; margin-right:-30px;border-top:dashed 1px #afaba8;border-bottom:dashed 1px #afaba8;height: 275px;}
		.gap {  margin-top: 10px; padding: auto;}
	</style>
	<script>
        $(function(){
            $("#txtUsername").blur(function(){
                var email=$(this).val();
                if(email==""){
                    $("#email").html("邮箱不能为空");
                    return;
                }
                //检查格式
                var pattern=/\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/;
                if(!pattern.test(email)){
                    $("#email").html("邮箱格式不正确");
                    return;
                }
            });
            $("#txtUsername").focus(function () {
                $("#email").html("");
            })
        });
	</script>
</head>
<body>
<%@include file="../common/headForUser.jsp"%>
<div class="bg">
	<div class="contain">
		<div class="row">
			<div class="medium-6 columns">
				<div class="introduce">
					<img src="../images/d_introduce.gif" />
					<ul style="list-style: none">
						<li class="tiaomu">
							更多选择
						</li>
						<li class="bj_none">
							60万种图书音像，共计百万种商品。
						</li>
						<li class="tiaomu">
							更低价格
						</li>
						<li class="bj_none">
							电视购物的3-5折，传统书店的5-7折，其他网站的7-9折。
						</li>
						<li class="tiaomu">
							更方便、更安全
						</li>
						<li class="bj_none">
							全国超过300个城市送货上门、货到付款。零风险购物，便捷到家。
						</li>
					</ul>
				</div>
			</div>
			<div class="medium-6 columns">
				<div class="login">
					<div style="height: 50px; padding: 5px; color: red" id="divErrorMssage"></div>
					<div class="main">
						<h5 class="gap">
							邮箱密码登录
						</h5>
						<div style="margin-left: 130px; height: 10px;">
							<span id="email" style="font-size:10px;color:red;"></span><br>
						</div>
						<form method="post" action="${pageContext.request.contextPath}/user/login.action" id="ctl00" class="gap">
							<ul class="no-bullet">
								<li>
									<div class="row collapse prefix-radius">
										<div class="small-2 columns">
											<span class="prefix"><i class="fi-torso"></i></span>
										</div>
										<div class="small-10 columns">
											<input type="text" name="name" id="txtUsername" placeholder="邮箱"/>
										</div>
									</div>
								</li>

								<li>
									<div class="row collapse prefix-radius">
										<div class="small-2 columns">
											<span class="prefix"><i class="fi-lock"></i></span>
										</div>
										<div class="small-10 columns">
											<input type="password" name="password" id="txtPassword" placeholder="密码"/>                                            </div>
									</div>
								</li>
								<li>
									<div class="right">
										<a href="${pageContext.request.contextPath}/user/pwdRefind.jsp">忘记密码?</a>
									</div>
								</li>
								<li>
									<div class="row" style="margin: 0; text-align:center">
										<input action=""
											   type="submit" id="btnSignCheck" class="button tiny" style="font-size: 16px; width: 100%"
											   value="登         录" />
									</div>
									<span id="fail" class="right" style="font-size:10px; color:red">${login_err}</span>


								</li>
							</ul>

						</form>
					</div>
					<div class="gap" style="margin-left: 65px;margin-bottom: 10px">
						<p >
							您还不是西电购书系统用户？
						</p>
						<div class="right" style="margin-right: -40px;font-size: 16px;">
							<a href="${pageContext.request.contextPath}/user/register_form.jsp">创建一个新用户&gt;&gt;</a>
						</div>
					</div>
				</div>
			</div>
		</div>
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