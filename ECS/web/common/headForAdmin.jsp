<%--
  Created by IntelliJ IDEA.
  User: wanghaogang
  Date: 2017/7/4
  Time: 下午12:26
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand"> 西电图书管理</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li id="home"><a href="../admin/admin.jsp">主页</a></li>
                <li id="userList"><a href="../admin/userList.action">用户列表</a></li>
                <li id="itemList"><a href="../admin/item_list.action">历史交易</a></li>
                <li class="dropdown">
                    <a id=bookList" href="#" class="dropdown-toggle" data-toggle="dropdown">
                        商品列表
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li id="bookListALL"><a href="../admin/bookList.action">查看所有</a></li>
                        <li class="divider"></li>
                        <li id="add"><a href="../admin/add_book.jsp">录入商品信息</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a><span class="glyphicon glyphicon-user"></span>管理员信息</a></li>
                <li><a href="../user/out.action"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li>
            </ul>
        </div>
    </div>
</nav>