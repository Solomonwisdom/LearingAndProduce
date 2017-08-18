<%@ page import="java.util.*" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <span class="navbar-brand"><s:property value="#session.user.nickname"/> 您好!欢迎光临西电购书系统</span>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <s:if test="#session.user.nickname!=null">
                    <li><a href="/user/out" class="b">登出</a></li>
                </s:if><s:else>
                <li><a href="../user/login_form.jsp">登录</a></li>
                <li><a href="../user/register_form.jsp" class="b">注册</a></li>
            </s:else>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="../cart/cartList"><span class="glyphicon glyphicon-shopping-cart"></span> 购物车</a></li>
                <li><a href="../cart/findAll.action?userId=${user.id }">我的西电&nbsp;&nbsp;</a></li>
            </ul>
        </div>
    </nav>
</div>
<div style="margin-top: -20px;">
    <div class="row head_head_list" style="background-color: #d8e4c6;">
        <div class="col-md-3">
            <div style="margin-top: 16px; margin-bottom: 10px">
                <a href="#" name="backtobook"><img
                        src="../images/booksaleimg/book_logo.gif" />
                </a>
            </div>
        </div>
    </div>
</div>
