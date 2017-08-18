<%@ page import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<style type="text/css">
		.red_bold{ color:#ff0000; font-weight:bold}
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
			<div class="panel panel-info">
				<div class="panel-heading">

					<h3 class="panel-title text-center">
						生成订单步骤:<span class="red_bold">1.确认订单</span> > 2.填写送货地址 > 3.订单成功
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
							<c:forEach  var="CartItem" items="${buyList}" varStatus="status">
								<tr>
									<td valign="top">
											${status.index+1}
									</td>
									<td valign="top">
											${CartItem.pro.productName }
									</td>
									<td valign="top">
											${CartItem.pro.dangPrice }
									</td>
									<td valign="top">
											${CartItem.qty }
									</td>
									<td valign="top">
											${CartItem.pro.dangPrice*CartItem.qty }
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td valign="top" class="w1" style="text-align: left" colspan="5">
									<b>总价￥${cost }</b>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="panel-footer">
					<div class="text-center">
						<a href="../cart/cartList.action"><input id="btnClientRegister" class="btn btn-default" name="submit"
														  type="submit" value="取消" /></a>

						<a href="address_form.jsp"><input id="btnClientRegister" class="btn btn-default" name="submit"
														  type="submit" value="下一步" onclick="location='address_form.jsp'"/></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

