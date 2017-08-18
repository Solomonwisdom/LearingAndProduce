<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户列表</title>
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/custom-styles.css">
    <script src="../assets/js/jquery.min.js"></script>
    <%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script>
        $("#userList").attr("href","");
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
<div id="wrapper">
<%@include file="../common/headForAdmin.jsp"%>
<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            用户列表
                        </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>邮箱</th>
                                <th>昵称</th>
                                <th>最后一次登录的时间</th>
                                <th>最后一次登录的IP</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="userList" var="user">
                                <tr>
                                <td>${user.id}</td>
                                <td>${user.email}</td>
                                <td>${user.nickname}</td>
                                <td>${user.lastLoginFormatTime}</td>
                                <td>${user.lastLoginIp}</td>
                                    <td>
                                        <a href="../admin/user_del.action?id=${user.id}">
                                            删除
                                        </a>
                                    </td>
                                </tr>
                            </s:iterator>

                            </tbody>
                            </table>
                        </div>
                    </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- DATA TABLE SCRIPTS -->
<script src="../assets/js/dataTables/jquery.dataTables.js"></script>
<script src="../assets/js/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables').dataTable();
    });
</script>
</body>
</html>
