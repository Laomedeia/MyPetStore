<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="${ctx}/cart/viewCart">
	返回购物车</a></div>

<div id="Catalog">

<table>
	<tr>
		<td>
		<h2>结算摘要</h2>

		<table>

			<tr>
				<th><b>货号</b></th>
				<th><b>产品代码</b></th>
				<th><b>描述</b></th>
				<th><b>有库存?</b></th>
				<th><b>数量</b></th>
				<th><b>单价</b></th>
				<th><b>总计</b></th>
			</tr>

			<c:forEach var="cartItem" items="${cartBean.cart.cartItems}">
				<tr>
					<td><a href="${ctx}/catalog/viewItem?itemId=${cartItem.item.itemId}">
				  ${cartItem.item.itemId}
			    </a></td>
					<td>${cartItem.item.product.productId}</td>
					<td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
					${cartItem.item.attribute3} ${cartItem.item.attribute4}
					${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
					<td>${cartItem.inStock}</td>
					<td>${cartItem.quantity}</td>
					<td><fmt:formatNumber value="${cartItem.item.listPrice}"
						pattern="$#,##0.00" /></td>
					<td><fmt:formatNumber value="${cartItem.total}"
						pattern="$#,##0.00" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">Sub Total: <fmt:formatNumber
					value="${cartBean.cart.subTotal}" pattern="$#,##0.00" /></td>
			</tr>
		</table>

		<td>&nbsp;</td>

	</tr>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>