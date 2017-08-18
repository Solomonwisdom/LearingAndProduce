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
    <title>找回密码</title>
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
        .bg {  min-width: 1000px;  max-width: 100%;  margin: 0px auto 0;  background: url("../images/bg.jpg") repeat #ffffff;  height: 75%;  padding: 10px 0 0 0;  }
        .contain { margin-left:8%;margin-right:8%; padding-top: 10px; }
        .logo { border-bottom: solid 3px #f27b21;padding-left: 5%; }
        .gap {  margin-top: 20px; padding: auto;}
        .bg .main{  margin: 30px 180px 40px 180px; padding:30px 340px 90px 340px; background-color: #FFFFFF}
    </style>
</head>
<body>
<%@include file="../common/headForUser.jsp"%>
<div class="bg">
    <form action="" method="post">
        <div class="main">
            <div class="row">
                <div class="row collapse prefix-radius">
                    <div class="medium-1 columns">
                        <span class="prefix"><i class="fi-torso"></i></span>
                    </div>
                    <div class="medium-9 columns">
                        <input type="text" name="name" id="txtUsername" placeholder="邮箱"/>
                    </div>
                    <div class="medium-2 columns">
                        <input type="button" class="button tiny radius" value="获取验证码">
                    </div>
                </div>
            </div>
            <div class="row gap">
                <input type="text" name="yzm" id="yzm" placeholder="请输入手机收到的验证码">
            </div>
            <div class="row gap">
                <input type="submit" class="button small radius" style="width: 100%">
            </div>
        </div>
    </form>
</div>
<%@include file="../common/footForUser.jsp"%>
</body>
</html>