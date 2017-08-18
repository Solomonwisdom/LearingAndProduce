<%@ page import="java.util.*" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>生成订单 - 西电网上购书系统</title>
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
	<script type="text/javascript">
        var flag={"name":false,"address":false,"code":false,"phone":false,"mobile":false};
        $(function(){
            $("#receiveName").blur(function(){
                flag.name=false;
                var name=$(this).val();
                if(name==""){
                    $("#name").html("收件人不能为空");
                    return;
                }
                flag.name=true;
            })
            $("#fullAddress").blur(function(){
                flag.address=false;
                var address=$(this).val();
                if(address==""){
                    $("#address1").html("地址不能为空");
                    return;
                }
                flag.address=true;
            })
            $("#postalCode").blur(function(){
                flag.code=false;
                var code=$(this).val();
                if(code==""){
                    $("#code").html("邮编不能为空");
                    return;
                }
                var pattern=/\b(^[0-9]{6}$)\b/;
                if(!pattern.test(code)){
                    $("#code").html("邮编格式错误");
                    return;
                }else{
                    $("#code").html("邮编格式正确");
                    flag.code=true;
                }
            })
            $("#phone1").blur(function(){
                flag.phone=false;
                var phone=$(this).val();
                if(phone==""){
                    $("#phone").html("电话不能为空");
                    return;
                }
                var pattern=/\b(^[0-9]{3,4}-[0-9]{8}$)\b/;
                if(!pattern.test(phone)){
                    $("#phone").html("电话格式错误");
                    return;
                }else{
                    $("#phone").html("电话格式正确");
                    flag.phone=true;
                }
            })
            $("#mobile1").blur(function(){
                flag.mobile=false;
                var mobile=$(this).val();
                if(mobile==""){
                    $("#mobile").html("手机不能为空");
                    return;
                }
                var pattern=/\b(^[0-9]{11}$)\b/;
                if(!pattern.test(mobile)){
                    $("#mobile").html("手机格式错误");
                    return;
                }else{
                    $("#mobile").html("手机格式正确");
                    flag.mobile=true;
                }
            });

        });

        $(function(){
            var userId=$("#userId").val();
            $.post(
                "../order/select_all",
                {"userId":userId},
                function(data){
                    var d=data.adds;
                    for(var i=0;i<d.length;i++){
                        var opt='<option value="'+d[i].id+'">'+d[i].receiveName+'</option>';
                        $("#address").append(opt);
                    }
                },"json"
            );
        })
        $(function(){
            $("#address").live("change",function(){
                var sid=$("#address").val();
                $.post(
                    "../order/select_findById",
                    {id:sid},
                    function(data){
                        var add=data.address;
                        $("#receiveName").val(add.receiveName);

                        $("#fullAddress").val(add.address);

                        $("#postalCode").val(add.postal);

                        $("#phone1").val(add.phone);

                        $("#mobile1").val(add.mobile);
                        var s=document.getElementById("address").value;
                        if(s==0){
                            $(".text_input").attr("disabled",false);
                        }else{
                            $(".text_input").attr("disabled",true);
                        }s
                    },"json"
                );
            });
        });
        $(function(){

            $("#f").submit(function(){
                var s=document.getElementById("address").value;
                if(s!=0){
                    $(".text_input").attr("disabled",false);
                    return true;
                }else{
                    var ok=flag.name&&flag.code&&flag.phone&&flag.address&&flag.mobile;
                    if(ok==false){
                        alert("表单项正在检测或存在错误！");
                        return false;
                    }
                    return true;
                }
            });

        });
	</script>
</head>
<body>
<div class="container-fluid">
	<%@include file="../common/headForUser.jsp"%>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						生成订单步骤: 1.确认订单 >
						<span class="red_bold"> 2.填写送货地址</span> > 3.订单成功
					</h3>
				</div>
				<div class="panel-body">
					<div>
						<form name="ctl00" method="post" action="../order/address.action" id="f">
							<input type="hidden" name="id" id="addressId" />
							<p>
								选择地址：
								<select id="address" name="select">
									<option value="0">
										填写新地址
									</option>
								</select>
							</p>
							<div class="table-responsive">
								<table class="table table-bordered" id="d1">
									<tr>
										<td valign="top" class="w1">
											收件人姓名：
										</td>
										<td>
											<input type="text" class="form-control" name="order.receiveName"
												   id="receiveName" />
										</td>
										<td>
											<div id="nameValidMsg">
												<p>
													请填写有效的收件人姓名
													<br/><span id="name" style="color:red"></span>
												</p>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" class="w1">
											收件人详细地址：
										</td>
										<td>
											<input type="text" name="order.address" class="form-control"
												   id="fullAddress" />
										</td>
										<td>
											<div id="addressValidMsg">
												<p>
													请填写有效的收件人的详细地址
													<br/><span id="address1" style="color:red"></span>
												</p>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" class="w1">
											邮政编码
										</td>
										<td>
											<input type="text" class="form-control" name="order.postal"
												   id="postalCode" />
										</td>
										<td>
											<div id="codeValidMsg">
												<p>
													请填写有效的收件人的邮政编码
													<br/><span id="code" style="color:red"></span>
												</p>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" class="w1">
											电话
										</td>
										<td>
											<input type="text" class="form-control" name="order.phone"
												   id="phone1" />
										</td>
										<td>
											<div id="phoneValidMsg">
												<p>
													请填写有效的收件人的电话
													<br/><span id="phone" style="color:red"></span>
												</p>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" class="w1">
											手机
										</td>
										<td>
											<input type="text" class="form-control" name="order.mobile"
												   id="mobile1" />
										</td>
										<td>
											<div id="mobileValidMsg">
												<p>
													请填写有效的收件人的手机
													<input type="hidden" value="${user.id}" id="userId"/>
													<br/><span id="mobile" style="color:red"></span>
												</p>
											</div>
										</td>
									</tr>
								</table>
							</div>
							<div class="text-center">

								<a href="../order/checkOut"><input id="btnClientRegister1" class="btn btn-default" name="submit"
																   type="button" value="取消" /></a>

								<input id="btnClientRegister2" class="btn btn-default" name="submit"
									   type="submit" value="下一步" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

