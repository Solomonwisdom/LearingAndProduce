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
    <title>录入书籍</title>
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/custom-styles.css">
    <script src="../assets/js/jquery.min.js"></script>
    <%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function select() {
            var idx = $("#num").val();
            var categorys = $("#category").val();
            if(categorys != "") {
                categorys += ",";
            }
            categorys += "" + idx;
            $("#category").val(categorys);
        }
        $("#add").attr("href","");
    </script>
</head>
<body>
<div id="wrapper">
    <%@include file="../common/headForAdmin.jsp"%>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">添加书籍</div>
                <div class="panel-body">
                    <form role="form" method="post" enctype="multipart/form-data" action="../admin/book_update.action">
                        <input type="number" class="hidden" name="book.id" value="${book.id}"/>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <div class="row">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="text-center">书籍名称</th><th class="text-center">作者</th>
                                            <th class="text-center">字数</th><th class="text-center">页数</th>
                                            <th class="text-center">原价</th><th class="text-center">售价</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td class="col-md-4">
                                                <input type="text" name="book.productName" id="productName" class="form-control" value="${book.productName}"/>
                                            </td>
                                            <td class="col-md-3">
                                                <input type="text" name="book.author" id="authortName" class="form-control" value="${book.author}"/>
                                            </td>
                                            <td class="col-md-2">
                                                <div class="row">
                                                    <div class="col-md-10">
                                                        <input type="text" name="wordNumber" id="wordNum" class="form-control" value="${wordNumber}"/>
                                                    </div>
                                                    <div class="col-md-1 col-md-pull-1" style="padding-top: 6px">万</div>
                                                </div>
                                            </td>
                                            <td class="col-md-1">
                                                <input type="number" name="book.totalPage" id="pageNum" class="form-control" value="${book.totalPage}"/>
                                            </td>
                                            <td class="col-md-1">
                                                <input type="text" name="book.fixedPrice" id="fixedPrice" class="form-control" value="${book.fixedPrice}"/>
                                            </td>
                                            <td class="col-md-1">
                                                <input type="text" name="book.dangPrice" id="dangPrice" class="form-control" value="${book.dangPrice}"/>
                                            </td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="row">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="text-center">版本号</th><th class="text-center">isbn</th>
                                            <th class="text-center">出版社</th><th class="text-center">出版时间</th>
                                            <th class="text-center">类别</th><th class="text-center">类别选择</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td class="col-md-1">
                                                <input type="number" name="book.whichEdtion" id="version" class="form-control" value="${book.whichEdtion}"/>
                                            </td>
                                            <td class="col-md-2">
                                                <input type="text" name="book.isbn" id="isbn" class="form-control" value="${book.isbn}"/>
                                            </td>
                                            <td class="col-md-3">
                                                <input type="text" name="book.publishing" id="publish" class="form-control" value="${book.publishing}"/>
                                            </td>
                                            <td class="col-md-2">
                                                <input type="date" name="publish_time" id="publis_time" class="form-control" value="${book.publishFormatTime}"/>
                                            </td>
                                            <td class="col-md-2">
                                                <input type="text" name="category" id="category" class="form-control" value="${category}"/>
                                            </td>
                                            <td class="col-md-2">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <select id="num" name="category_select" onchange="select()"  class="form-control">
                                                            <option value="1">图书</option><option value="2">小说</option><option value="3">青春</option>
                                                            <option value="4">人文社科</option><option value="5">管理</option><option value="6">少儿</option>
                                                            <option value="7">外语</option><option value="8">计算机</option><option value="9">当代小说</option>
                                                            <option value="10">近现代小说</option><option value="11">古典小说</option><option value="12">四大名著</option>
                                                            <option value="13">世界名著</option><option value="14">校园</option><option value="15">爱情/情感</option>
                                                            <option value="16">叛逆/成长</option><option value="17">玄幻</option><option value="18">原创</option>
                                                            <option value="19">政治</option><option value="20">经济</option><option value="21">法律</option>
                                                            <option value="22">哲学</option><option  value="23">历史</option><option  value="24">管理学</option>
                                                            <option value="25">战略管理</option><option value="26">市场营销</option><option value="27">商业史传</option>
                                                            <option value="28">电子商务</option><option value="29">0-2岁</option><option value="30">3-6岁</option>
                                                            <option value="31">7-10岁</option><option value="32">11-14岁</option><option value="33">儿童文学</option>
                                                            <option value="34">英语</option><option value="35">日语</option><option value="36">韩语</option>
                                                            <option value="37">俄语</option><option value="38">德语</option><option value="39">计算机理论</option>
                                                            <option value="40">数据库</option><option value="41">程序设计</option><option value="42">人工智能</option>
                                                            <option value="43">计算机考试</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="row">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="text-center">描述</th><th class="text-center">作者简介</th>
                                            <th class="text-center">图片</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td class="col-md-5">
                                                <input type="text" name="book.description" id="description"  class="form-control" value="${book.description}"/>
                                            </td>
                                            <td class="col-md-4">
                                                <input type="text" name="book.authorSummary" id="author_intro" class="form-control" value="${book.authorSummary}"/>
                                            </td>
                                            <td class="col-md-3">
                                                <input type="file" name="file1" id="file1" class="form-control"/>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="row">
                                    <input type="submit" id="btnAddCheck" class="btn btn-block btn-primary pull-right form-control" value="提交">
                                </div>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>