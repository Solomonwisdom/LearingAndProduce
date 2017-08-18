<%@page import="java.util.*" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<row>
	<c:forEach var="product" items="${pros}" varStatus="status">
		<c:if test="${status.index<4}">
			<div class="col-md-3">
				<div class="row tidy">
					<div class="col-sm-12 text-center">
						<a  href='#' target='_blank'><img src="../productImages/${product.productPic }" width=80 border=0 /> </a><br>
						<span><a href='#' target='_blank'>${product.productName }</a></span>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<ul class="list-unstyled">
							<li><small>定价:￥${product.fixedPrice }</small></li>
							<li><small>西电价:￥${product.dangPrice }</small></li>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>
</row>
<row>
	<c:forEach var="product" items="${pros}" varStatus="status">
		<c:if test="${status.index>=4}">
			<div class="col-md-3">
				<div class="row tidy">
					<div class="col-sm-12 text-center">
						<a  href='#' target='_blank'><img src="../productImages/${product.productPic }" width=80 border=0 /> </a><br>
						<span><a href='#' target='_blank'>${product.productName }</a></span>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<ul class="list-unstyled">
							<li><small>定价:￥${product.fixedPrice }</small></li>
							<li><small>西电价:￥${product.dangPrice }</small></li>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>
</row>