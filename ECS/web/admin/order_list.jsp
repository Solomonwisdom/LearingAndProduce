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
    <title>历史交易列表</title>
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
                                历史交易列表
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables">
                                        <thead>
                                        <tr>
                                            <th>编号</th>
                                            <th>商品名称</th>
                                            <th>商品单价</th>
                                            <th>商品数量</th>
                                            <th>小计</th>
                                            <th>用户编号</th>
                                            <th>用户邮箱</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="itemList" var="item">
                                            <tr>
                                                <td>${item.id}</td>
                                                <td>${item.productName}</td>
                                                <td>${item.dangPrice}</td>
                                                <td>${item.productNum}</td>
                                                <td>${item.amount}</td>
                                                <td>${item.userId}</td>
                                                <td>${item.email}</td>
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
