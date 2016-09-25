<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<h2>我的订单</h2>

<table>
	<tr>
		<th>订单号</th>
		<th>日期</th>
		<th>总价</th>
	</tr>

	<c:forEach var="order" items="${orderList}">
		<tr>
			<td><a href="${ctx}/order/viewOrder?orderId=${order.orderId}">
			    ${order.orderId}
			  </a></td>
			<td><fmt:formatDate value="${order.orderDate}"
				pattern="yyyy/MM/dd hh:mm:ss" /></td>
			<td><fmt:formatNumber value="${order.totalPrice}"
				pattern="$#,##0.00" /></td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../common/IncludeBottom.jsp"%>


