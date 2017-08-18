<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="list-unstyled">
    <c:forEach  var="product" items="${newHots}" varStatus="status">
    <li>
        <c:if test="${status.index<3}"> <img src="../images/book_no0_${status.index+1}.gif" ></c:if>
        <c:if test="${status.index>=3}"><img src="../images/book_no${status.index+1}.gif" ></c:if>
        <a href="#">${product.productName}</a></td>
    </li>
    </c:forEach>
</ul>