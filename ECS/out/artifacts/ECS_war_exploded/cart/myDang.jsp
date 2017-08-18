<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>生成订单 - 西电购书系统</title>
	<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="../assets/css/custom-styles.css">
	<script src="../assets/js/jquery.min.js"></script>
	<%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
	<script src="../assets/js/bootstrap.min.js"></script>
	<style>
		.contain { margin-left:9%;margin-right:9%; padding-top: 10px; background: #FFFFFF }
		.gap {  margin-top: 10px; padding: auto;}
		.logo { border-bottom: solid 3px #f27b21;padding-left: 5%; }
		body {  background: #a8cfbf; }
		.panel{  border-radius:0;  }
		.container-fluid{margin-top:1%; }
	</style>
</head>
<body>
<div class="container-fluid">
	<%@include file="../common/headForUser.jsp"%>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						历史购买商品
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
							<tr>
								<th valign="top" class="w1" style="text-align: left">
									<b>序号</b>
								</th>
								<th valign="top" class="w1" style="text-align: left">
									<b>商品名称</b>
								</th>
								<th valign="top" class="w1" style="text-align: left">
									<b>商品单价</b>
								</th>
								<th valign="top" class="w1" style="text-align: left">
									<b>商品数量</b>
								</th>
								<th valign="top" class="w1" style="text-align: left">
									<b>小计</b>
								</th>
							</tr>
							</thead>
							<tbody>
							<!-- 订单开始 -->
							<c:forEach  var="item" items="${items}" varStatus="status">
								<tr>
									<td valign="top">
											${status.index+1}
									</td>
									<td valign="top">
											${item.productName }
									</td>
									<td valign="top">
											${item.dangPrice }
									</td>
									<td valign="top">
											${item.productNum }
									</td>
									<td valign="top">
											${item.amount }
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="panel-footer">
					<div class="text-right">
						<a href="../main/main.jsp"><input id="btnClientRegister" class="btn btn-success " name="submit"
														  type="submit" value="返回" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

