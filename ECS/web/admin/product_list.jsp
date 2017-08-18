<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>商品列表</title>
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/custom-styles.css">
    <script src="../assets/js/jquery.min.js"></script>
    <%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script>
        $(function(){
            $("#bookList").attr("href","");
        })
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/headForAdmin.jsp"%>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                图书列表
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">

                                        <thead>
                                        <tr>
                                            <th>编号</th>
                                            <th>书名</th>
                                            <th>作者</th>
                                            <th>isbn</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <s:iterator value="bookList" var="book">
                                            <tr>
                                                <td>${book.id}</td>
                                                <td>${book.productName}</td>
                                                <td>${book.author}</td>
                                                <td>${book.isbn}</td>
                                                <td>
                                                    <a href="../admin/book_detail.action?id=${book.id}">
                                                        更改
                                                    </a>/
                                                    <a href="../admin/book_delete.action?id=${book.id}">
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
        $('#dataTables-example').dataTable();
    });
</script>
</body>
</html>
