<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="panel panel-default" style="margin-left: 6%">
    <div class="panel-heading">分类浏览</div>
    <div class="panel-body">
        <ul class="list-group">
            <s:iterator value="cats" var="cat1">
                <li class="list-group-item">
                    <h5>
                        [<a href='#'>${cat1.name}</a>]
                    </h5>
                    <ol class="breadcrumb">
                        <s:iterator value="#cat1.subCats" var="cat2">
                            <li>
                                <a href="../main/bookList?pid=${cat1.id }&id=${cat2.id }">${cat2.name}</a>
                            </li>
                        </s:iterator>
                    </ol>
                </li>
            </s:iterator>
        </ul>
    </div>
</div>