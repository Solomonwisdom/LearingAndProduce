<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>西电购书系统 – 全球最大的中文网上书店</title>

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
		.del{ color:#404040; text-decoration:line-through; }
	</style>
	<script type="text/javascript">
        $(function(){
            $(".buybtn").click(function() {
                var id=$(this).attr("id");
                $.post(
                    "../cart/buy",
                    {"pid":id},
                    function(data){
                        if(data){
                            $("#cartinfo"+id).html("成功放入购物车");

                        }else{
                            $("#cartinfo"+id).html("购物车中已存在");
                        }
                    },"json"
                );
                return false;
            })
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
						<div class="col-md-4">
							<ol class="breadcrumb">
								您现在的位置:&nbsp;
								<li><a href="main.jsp">西电图书</a></li>
								<li><a href="#">小说</a> </li>
							</ol>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<div style="margin-right: 10px">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color: #FF6600; color: #ffffff">分类浏览</div>
									<div>
										<ul class="list-group">
											<li class="list-group-item list-item-g">
												<span class="badge">${totalNum }</span>
												<a href="#">&middot;全部</a>
											</li>
											<s:iterator value="cats">
												<li class="list-group-item list-item-g">
													<span class="badge">${pnum }</span>
													<a href="bookList?pid=${pid }&id=${id }">&middot;${name}</a>
												</li>
											</s:iterator>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-9">
							<div class="panel panel-default">
								<div class="panel-heading" style="background-color: #bEbEbE;color: #ac2925;">商品目录</div>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table" id="dataTables">
											<thead>
											<tr>
												<th>封面</th><th>介绍</th><th>购买</th>
											</tr>
											</thead>
											<tbody>
											<s:iterator value="books" >
												<tr>
													<td>
														<a href='#'><img src="../productImages/${productPic }" width=70 border=0 /> </a>
													</td>
													<td>
														<h4 class="upmove">
															<a href='#'>${productName}</a>
														</h4>
														<h5>
															顾客评分：100
														</h5>
														<h5>
															作 者:${anthor }
														</h5>
														<h5>
															出版社：${publishing }
														</h5>
														<h5>
															出版时间：${publishFormatTime }
														</h5>
														<h6>
																${description }
														</h6>
													</td>
													<td>
														<div style="padding-top: 100px">
															<span class="del">￥${fixedPrice}</span>
															<span>￥${dangPrice}</span>
															节省：￥${fixedPrice-dangPrice }
															<a class="buybtn" id="${id }" href="#">
																<img src='../images/buttom_goumai.gif'/> </a>
															<span id="cartinfo${id}"></span>
														</div>
													</td>
												</tr>
											</s:iterator>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

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
        //language=JQuery-CSS
        $("#dataTables").dataTable();
    });
</script>
</body>
</html>
