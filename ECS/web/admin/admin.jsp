<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>后台控制主页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/custom-styles.css">
    <script src="../assets/js/jquery.min.js"></script>
    <%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script>
        $("#home").attr("href","");
    </script>
</head>

<body>
<div id="wrapper">
    <%@include file="../common/headForAdmin.jsp"%>
    <div class="text-center" style="color: #28a4c9; padding-top: 20%;">
        <h1>欢迎来到西电后台</h1>
    </div>
</div>
</body>
</html>
