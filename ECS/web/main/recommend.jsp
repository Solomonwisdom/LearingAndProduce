<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<ul class="list-group">
	<s:iterator value="books">
	<li class="list-group-item">
		<div class="row">
			<div class="col-sm-2">
				<a href='#' target='_blank'><img src="../productImages/${productPic}" width=70 border=0 /> </a>
			</div>

			<div class="col-sm-10">
				<h4 class="upmove">
					<a href='#' target='_blank'>${productName }</a>
				</h4>
				<h5 style="chara">
					作者：${author } 著
				</h5>
				<h5>
					出版社：${publishing }
				</h5>
				<h6>
						${catalogue }
				</h6>
				<h6>
					定价：￥${fixedPrice }&nbsp;&nbsp;西电价：￥${dangPrice }
				</h6>
			</div>
		</div>
	</li>
	</s:iterator>
</ul>