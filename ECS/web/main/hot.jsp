<%@page import="java.util.*" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div class="row">
	<s:iterator value="hots">
		<div class="col-md-3">
			<div class="row tidy">
				<div class="col-sm-12 text-center">
					<a  href='#' target='_blank'><img src="../productImages/${productPic }" width=80 border=0 /> </a><br>
					<span><a href='#' target='_blank'>${productName }</a></span>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<ul class="list-unstyled">
						<li><small>定价:￥${fixedPrice }</small></li>
						<li><small>西电价:￥${dangPrice }</small></li>
					</ul>
				</div>
			</div>
		</div>
	</s:iterator>
</div>