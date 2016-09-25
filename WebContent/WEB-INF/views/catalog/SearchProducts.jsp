<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="${ctx}/catalog/viewMain">
	返回主页
</a></div>

<div id="Catalog">

<table>
	<tr>
		<th>&nbsp;</th>
		<th>产品号</th>
		<th>名称</th>
	</tr>
	<c:forEach var="product" items="${productList}">
		<tr>
			<td><a href="${ctx}/catalog/viewProduct?productId=${product.productId}">
				${product.description}
			</a></td>
			<td><b> <a href="${ctx}/catalog/viewProduct?productId=${product.productId}">
				<font color="BLACK"> ${product.productId} </font>
			</a> </b></td>
			<td>${product.name}</td>
		</tr>
	</c:forEach>
	<tr>
		<td></td>
	</tr>

</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>




