<%@ page import="java.util.*" language="java" pageEncoding="UTF-8" %>
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
						生成订单步骤:1.确认订单 > 2.填写送货地址 > <span class="red_bold">3.订单成功</span>
					</h3>
				</div>
				<div class="panel-body">
					<div class="text-center">
						<div style="font-size:30px">订单成功

						</div>
						<h5>
							订单已经生成
						</h5>
						<h6>
							您刚刚生成的订单号是：${order_id }，金额总计￥${cost };
						</h6>

						<ul class="list-inline">
							<li>
								您现在可以：
							</li>
							<li>
								<a href="../main/main.jsp">继续浏览并选购商品</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>

