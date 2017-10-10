<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8"%>
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
		.logo { border-bottom: solid 3px #f27b21;padding-left: 5%; }
		input{vertical-align: middle; font-size:12px}
		body{font-family: "宋体", Arial, Helvetica, sans-serif; margin:auto; font-size:14px; background:#fff; color:#414141}
		.login_step{ height:30px; font-size:18px; text-align:center; padding-top:10px;  padding-bottom: 10px;}
		.red_bold{ color:#ff0000; font-weight:bold}
		.contain { margin-left:5%;margin-right:5%; padding-top: 10px; }
		.bg {  min-width: 1000px;  max-width: 100%;  margin: 0px auto 0;  background: url("../images/bg.jpg") repeat #ffffff;  height: 75%;  padding: 10px 0 0 0;  }
		.gap {  margin-top: 10px; padding: auto;}
		.tab_register{ width: 100%; border-collapse:collapse; }
		.text_left{ float:left; padding-left:20px;word-break:break-all;font-size: 14px}
		.text_left span {font-size: 12px}
		.yzm_img{ width:100px; height:30px; float:left; margin-left:20px}
		.tab_register .tdh{ width:160px; padding-right:0; text-align:right; font-size:16px; background-color:#e8fbff}
	</style>
	<script type="text/javascript">
        var flag={"email":false,"password":false,"name":false,"pic":false};
        //验证码
        $(function(){
            flag.pic=false;
            $("#change").click(function(){
                $("#code").attr("src","imagecode.action?dt="+new Date().getTime());
                return false;

            });
            $("#change1").click(function(){
                $("#change").click();

            });

        });
        $(function(){
            $("#code_text").blur(function(){
                var code=$("#code_text").val();
                if(code==""){
                    $("#code_span").html("验证码不能为空");
                    return;
                }
                $.post(
                    "checkcode.action",
                    {"code":code},
                    function(data){
                        if(data==true){
                            $("#code_span").html("验证码正确！");
                            flag.pic=true;
                        }else{
                            $("#code_span").html("验证码错误！");
                        }
                    },"json"
                );
            });
        });
        //验证码

        $(function(){
            $("#txtEmail").blur(function(){
                flag.email=false;
                var email=$(this).val();
                if(email==""){
                    $("#email\\.info").html("邮箱不能为空");
                    return;
                }
                //检查格式
                var pattern=/\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/;
                if(!pattern.test(email)){
                    $("#email\\.info").html("邮箱格式不正确");
                    return;
                }
                //检查唯一性
                $.post(
                    "../user/checkemail.action",
                    {"email":email},
                    function(data){
                        if(data){
                            $("#email\\.info").html("邮箱可以使用");
                            flag.email=true;
                        }else{
                            $("#email\\.info").html("邮箱已经被使用");
                        }
                    },"json"
                );
            });
            //昵称不能为空
            $("#txtNickName").blur(function(){
                flag.name=false;
                var name=$(this).val();
                if(name==""){
                    $("#name\\.info").html("昵称不能为空");
                    return;
                }
                if(name.length<3||name.length>20){
                    $("#name\\.info").html("昵称格式不正确");
                }else{
                    $("#name\\.info").html("昵称可以使用");
                    flag.name=true;
                }
            });
            //密码设置
            $("#txtPassword").blur(function(){

                var password=$(this).val();
                if(password==""){
                    $("#password\\.info").html("密码不能为空");
                    return;
                }
                //密码格式
                var pattern=/\b(^[A-Za-z0-9]{6,20}$)\b/;
                if(!pattern.test(password)){
                    $("#password\\.info").html("密码格式不正确");
                    return;
                }else{
                    $("#password\\.info").html("密码格式正确");
                }
            });
            //重复密码
            $("#txtRepeatPass").blur(function(){
                flag.password=false;
                password=$("#txtPassword").val();
                pwd=$("#txtRepeatPass").val();
                if(pwd==""){
                    $("#password1\\.info").html("密码不能为空");
                    return;
                }
                if(!(password==pwd)){
                    $("#password1\\.info").html("密码不一致！请重新输入");
                }else{
                    $("#password1\\.info").html("密码可以使用");
                    flag.password=true;
                }
            });
        });
        $(function(){
            $("#f").submit(function(){
                var ok=flag.email&&flag.password&&flag.name&&flag.pic;
                if(ok==false){
                    alert("表单项正在检测或存在错误！");
                    return false;
                }
                return true;
            });
        });
	</script>
</head>
<body>
<%@include file="../common/headForUser.jsp"%>
<div class="bg">
	<div class="row text-center">
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> > 2.验证邮箱 > 3.注册成功
		</div>
	</div>
	<div class="row gap">
		<div class="contain" >
			<form name="ctl00" method="post" class="tab-register"
				  action="${pageContext.request.contextPath}/user/regist.action" id="f">
				<table class="tab_register">
					<tr>
						<td valign="top" class="tdh">
							请填写您的Email地址：
						</td>
						<td>
							<div class="row collapse prefix-radius">
								<div class="small-2 columns">
									<span class="prefix"><i class="fi-mail"></i></span>
								</div>
								<div class="small-10 columns">
									<input name="user.email" type="text" id="txtEmail" />
								</div>
							</div>
						</td>
						<td>
							<div class="text_left" id="emailValidMsg">
								请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件<br>
								<span id="email.info" style="color:red">${regist_error}</span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="tdh">
							设置您在西电购书系统的昵称：
						</td>
						<td>
							<div class="row collapse prefix-radius">
								<div class="small-2 columns">
									<span class="prefix"><i class="fi-torso"></i></span>
								</div>
								<div class="small-10 columns">
									<input name="user.nickname" type="text" id="txtNickName"/>
								</div>
							</div>
						</td>
						<td>
							<div class="text_left" id="nickNameValidMsg">
								您的昵称可以由小写英文字母、中文、数字组成，<br>
								长度4－20个字符，一个汉字为两个字符。<br>
								<span id="name.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="tdh">
							设置密码：
						</td>
						<td>
							<div class="row collapse prefix-radius">
								<div class="small-2 columns">
									<span class="prefix"><i class="fi-lock"></i></span>
								</div>
								<div class="small-10 columns">
									<input name="user.password" type="password" id="txtPassword"/>
								</div>
							</div>
						</td>
						<td>
							<div class="text_left" id="passwordValidMsg">
								您的密码可以由大小写英文字母、数字组成，长度6－20位。<br>
								<span id="password.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="tdh">
							再次输入您设置的密码：
						</td>
						<td>
							<div class="row collapse prefix-radius">
								<div class="small-2 columns">
									<span class="prefix"><i class="fi-lock"></i></span>
								</div>
								<div class="small-10 columns">
									<input name="password1" type="password" id="txtRepeatPass"/>
								</div>
							</div>
						</td>
						<td>
							<div class="text_left" id="repeatPassValidMsg">
								<span id="password1.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="tdh">
							验证码：
						</td>
						<td>
							<div class="row">
								<div class="small-5 columns">
									<input name="code_text" type="text" id="code_text"/>
								</div>
								<div class="small-7 columns">
									<a id="change1"><img class="yzm_img" id="code" src="imagecode.action" /></a>
								</div>
							</div>
						</td>
						<td>
							<div class="text_left">
								<span id="code_span" style="color:red"></span><br>
								<div class="text_left" id="vcodeValidMsg">请输入图片中的六个符号。</div>
								<a id="change" href="#">看不清楚？换个图片</a>
							</div>
						</td>
					</tr>
				</table>
				<div class="row text-center">
					<input id="btnClientRegister" class="button tiny radius" style="font-size: 16px" name="submit"
						   type="submit" value="下    一    步" />
				</div>
			</form>
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

