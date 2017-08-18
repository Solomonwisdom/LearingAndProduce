<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div>
	<ul class="list-group">
		<!--1级分类开始-->
		<s:iterator value="cats" var="c1">
		<li class="list-group-item list-item-g">
			<h5 class="panel-title">
				[<a href='#'>${c1.name}</a>]
			</h5>
			<ol class="breadcrumb list-item-g">
				<!--2级分类开始-->
				<s:iterator value="#c1.subCats" var="c2">
				<li>
					<a href='../main/bookList?pid=${c1.id }&id=${c2.id }'>${c2.name}</a>
				</li>
				</s:iterator>
				<!--2级分类结束-->
			</ol>
		</li>
		</s:iterator>
		<!--1级分类结束-->
	</ul>
</div>