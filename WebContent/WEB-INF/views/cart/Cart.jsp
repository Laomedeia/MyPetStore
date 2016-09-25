<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="${ctx}/catalog/viewMain">
	回到主页</a></div>

<div id="Catalog">

<div id="Cart">

<h2>购物车</h2>
<form method="post" action="${ctx}/cart/updateCartQuantities">
	<table>
		<tr>
			<th><b>货号</b></th>
			<th><b>产品代码</b></th>
			<th><b>描述</b></th>
			<th><b>有库存?</b></th>
			<th><b>数量</b></th>
			<th><b>单价</b></th>
			<th><b>总计</b></th>
			<th>&nbsp;</th>
		</tr>

		<c:if test="${cartBean.cart.numberOfItems == 0}">
			<tr>
				<td colspan="8"><b>您的购物车是空的.</b></td>
			</tr>
		</c:if>

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
				<td><input type="text" size="3" name="${cartItem.item.itemId}"
					value="${cartItem.quantity}" /></td>
				<td><fmt:formatNumber value="${cartItem.item.listPrice}"
					pattern="$#,##0.00" /></td>
				<td><fmt:formatNumber value="${cartItem.total}"
					pattern="$#,##0.00" /></td>
				<td><a class="Button" href="${ctx}/cart/removeItemFromCart?workingItemId=${cartItem.item.itemId}">
            	Remove
            </a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7">Sub Total: <fmt:formatNumber
				value="${cartBean.cart.subTotal}" pattern="$#,##0.00" /> <input type="submit"
				name="updateCartQuantities" value="更新购物车" /></td>
			<td>&nbsp;</td>
		</tr>
	</table>

</form> <c:if test="${cartBean.cart.numberOfItems > 0}">
	<a class="Button" href="${ctx}/order/newOrderForm">
      	前去结算
      </a>
</c:if></div>

<div id="MyList">
  <c:if test="${accountBean != null}">
	<c:if test="${!accountBean.authenticated}">
	  <c:if test="${!empty accountBean.account.listOption}">
	    <%@ include file="IncludeMyList.jsp"%>
      </c:if>
	</c:if>
  </c:if>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>