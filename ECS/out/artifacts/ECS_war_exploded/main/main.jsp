<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>西电图书 – 全球最大的中文网上书店</title>
	<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="../assets/css/custom-styles.css">
	<link rel="stylesheet" href="../assets/css/style.css">
	<script src="../assets/js/jquery.min.js"></script>
	<%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
	<script src="../assets/js/bootstrap.min.js"></script>
	<style>
		body {  background: #a8cfbf; }
		.panel{  border-radius:0px;  }
		.container-fluid{margin-top:1%; }
		.list-item-g{background-color: #faf2cc; max-height: 105px}
		.breadcrumb{font-size: small}
		.tidy{  min-height:155px;}
		.head_head_list{border-bottom:1px solid #000d04;border-left:1px solid #053b13;border-right:1px solid #053b13;border-top:1px solid #000d04;margin: 0 auto; background:#d8e4c6;}
		.upmove{margin-top:-8px}
	</style>
	<script>
        $(function(){
            $("#new").load("/main/findNew.action");
        })
	</script>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<%@include file="../common/headForShop.jsp"%>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3">
							<div style="margin-right: 10px">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color: #FF6600; color: #ffffff">分类浏览</div>
									<s:action name="findCategory" namespace="/main" executeResult="true"/>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<!--推荐图书开始-->
							<div class="panel panel-default">
								<div class="panel-heading" style="background-color: #bEbEbE;color: #ac2925;">随机推荐</div>
								<div class="panel-body">
									<s:action name="findBook" namespace="/main" executeResult="true"/>
								</div>
							</div>
							<!--推荐图书结束-->

							<!--热销图书开始-->
							<div class="panel panel-default">
								<div class="panel-heading" style="background-color: #bEbEbE;color: #ac2925;">热销图书</div>
								<div class="panel-body">
									<s:action name="findHot" namespace="/main" executeResult="true"/>
								</div>
							</div>
							<!--热销图书结束-->

							<!--最新上架图书开始-->
							<div class="panel panel-default">
								<div class="panel-heading" style="background-color: #bEbEbE;color: #ac2925;">最新上架图书</div>
								<div class="panel-body" id="new">
									<img src="../images/window_loading.gif"/>正在努力加载………………………………
								</div>
							</div>
							<!--最新上架图书结束-->
						</div>

						<div class="col-md-3">
							<div class="panel panel-danger">
								<div class="panel-heading">新书热卖榜</div>
								<div class="panel-body">
									<s:action name="findNewHot" namespace="/main" executeResult="true"/>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
