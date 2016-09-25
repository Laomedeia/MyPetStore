<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="catalog"
	class="com.mystore.petstore.controller.CatalogController" />

<div id="BackLink"><a href="${ctx}/catalog/viewCategory?categoryId=${product.categoryId}">
	返回  ${product.categoryId}
</a></div>

<div id="Catalog">

<h2>${product.name}</h2>

<table>
	<tr>
		<th>货号</th>
		<th>产品号</th>
		<th>描述</th>
		<th>价格</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${itemList}">
		<tr>
			<td><a href="${ctx}/catalog/viewItem?itemId=${item.itemId}">
				${item.itemId}
			</a></td>
			<td>${item.product.productId}</td>
			<td>${item.attribute1} ${item.attribute2} ${item.attribute3}
			${item.attribute4} ${item.attribute5} ${product.name}</td>
			<td><fmt:formatNumber value="${item.listPrice}"
				pattern="$#,##0.00" /></td>
			<td><a class="Button" href="${ctx}/cart/addItemToCart?workingItemId=${item.itemId}">
        	添加到购物车
        </a></td>
		</tr>
	</c:forEach>
	<tr>
		<td>
		</td>
	</tr>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>





