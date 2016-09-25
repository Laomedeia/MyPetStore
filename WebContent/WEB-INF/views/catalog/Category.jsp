<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="${ctx}/catalog/viewMain">
	回到主页</a></div>

<div id="Catalog">

<h2>${category.name}</h2>

<table>
	<tr>
		<th>产品号</th>
		<th>名称</th>
	</tr>
	<c:forEach var="product" items="${productList}">
		<tr>
			<td><a href="${ctx}/catalog/viewProduct?productId=${product.productId}">
				${product.productId}
			</a></td>
			<td>${product.name}</td>
		</tr>
	</c:forEach>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


