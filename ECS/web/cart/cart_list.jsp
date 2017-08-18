<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>西电图书 – 全球最大的中文网上书店</title>
	<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="../assets/css/custom-styles.css">
	<script src="../assets/js/jquery.min.js"></script>
	<%--<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>--%>
	<script src="../assets/js/bootstrap.min.js"></script>
	<style>
		body {  background: #a8cfbf; }
		.panel{  border-radius:0;  }
		.container-fluid{margin-top:1%; }
	</style>
	<script type="text/javascript">
        //写一个除掉空格的函数
        function strip(str){
            var reg = /\s*/g;
            return str.replace(reg,'');
        }

        function change(id,qty){
            //非空验证
            if(strip(qty).length == 0){
                alert('数量必须输入');

                return;
            }
            if(strip(qty)==0){
                alert('别开玩笑了！');
                return;
            }
            //必须是数字
            var reg = /^[0-9]+$/;
            //reg.test(string): 如果string匹配正则
            //表达式的要求，返回true,否则，返回false
            if(!reg.test(strip(qty))){
                alert('必须是数字');

                return;
            }
            location="/cart/cart_modify?pid="+id+"&qty="+qty;
        }
	</script>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">
					<img class="pic_shop" src="../images/pic_myshopping.gif" />
				</div>
				<div class="panel-body">
					<div class="panel panel-success">
						<div class="panel-heading">
							您已选购以下商品
						</div>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table" id="tbCartItemsNormal">
								<thead>
								<tr>
									<th>
										<span>&nbsp;</span>
									</th>
									<th>
										<span>商品名</span>
									</th>
									<th>
										<span>市场价</span>
									</th>
									<th>
										<span>西电价</span>
									</th>
									<th>
										<span>数量</span>
									</th>
									<th>
										<span>变更数量</span>
									</th>
									<th>
										<span>删除</span>
									</th>
								</tr>
								</thead>

								<tbody>
								<!-- 购物列表开始 -->
								<s:iterator value="buyList">
									<tr>
										<td style='display: none'>

										</td>
										<td>
											<span><img /> </span>
										</td>
										<td>
											<a href="#">${pro.productName }</a>
										</td>
										<td>
											<span>￥${pro.fixedPrice}</span>
										</td>
										<td>
											&nbsp;&nbsp;
											<span>￥${pro.dangPrice}</span>
										</td>
										<td>
											&nbsp;&nbsp;${qty }
										</td>

										<td >
											<input type="text" size="3" maxlength="4" id="qty_${pro.id }" />
											<a  href="javascript:;" onclick="change(${pro.id },document.getElementById('qty_${pro.id }').value)">变更</a>

										</td>
										<td>
											<a href="../cart/cart_delete.action?pid=${pro.id }">删除</a>
										</td>
									</tr>
								</s:iterator>
								</tbody>


								<!-- 购物列表结束 -->
							</table>
						</div>
					</div>

					<div class="panel-footer">
						<s:if test="size==0">
							<div class="text-center">
								您还没有挑选商品
								<a href='../main/main.jsp'> 继续挑选商品>></a>
							</div>
						</s:if>
						<s:if test="size!=0">
							<div class="row">
								<div class="col-md-2">
									<a href='../main/main.jsp'> 继续挑选商品>></a>
								</div>

								<div class="col-md-5 col-md-offset-5">
									<div>
										您共节省：
										<span> ￥<span id="total_economy">${sale}</span></span>
										<span style="font-size: 14px">|</span>
										<span>商品金额总计：</span>
										<span> ￥<span id='total_account'>${cost}</span></span>
										<a name='checkout' href='../order/checkOut.action' >
											<img src="../images/butt_balance.gif" alt="结算" border="0" title="结算" />
										</a>
									</div>
								</div>
							</div>
						</s:if>
					</div>
					<div class="panel"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">
								您已删除以下商品，如果想重新购买，请点击“恢复”
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-responsive" id=del_table>
								<tbody>

								<s:iterator value="delList">
									<tr>
										<td>
											&nbsp;${pro.id }
										</td>
										<td>
											<a href="#">${pro.productName }</a>
										</td>
										<td>
											￥${pro.fixedPrice }
										</td>
										<td>
											<span>￥${pro.dangPrice}</span>
										</td>
										<td>
											<a href="../cart/cart_recovery.action?pid=${pro.id }">恢复</a>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</s:iterator>


								<tr>
									<td colspan=8>
										&nbsp;
									</td>
								</tr>


								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="text-center">
						<a href="../main/main.jsp"><input id="btnClientRegister" class="btn btn-default " name="submit"
														  type="submit" value="返回" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>