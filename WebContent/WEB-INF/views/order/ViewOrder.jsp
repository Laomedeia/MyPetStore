<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="${ctx}/catalog/viewMain">
	回到首页</a></div>

<div id="Catalog">

<table>
	<tr>
		<th align="center" colspan="2">Order #${order.orderId}
		<fmt:formatDate value="${order.orderDate}"
			pattern="yyyy/MM/dd hh:mm:ss" /></th>
	</tr>
	<tr>
		<th colspan="2">支付细节</th>
	</tr>
	<tr>
		<td>支付卡类型:</td>
		<td><c:out value="${order.cardType}" /></td>
	</tr>
	<tr>
		<td>卡号:</td>
		<td><c:out value="${order.creditCard}" /> * Fake
		number!</td>
	</tr>
	<tr>
		<td>卡到期日 (MM/YYYY):</td>
		<td><c:out value="${order.expiryDate}" /></td>
	</tr>
	<tr>
		<th colspan=2>账单地址</th>
	</tr>
	<tr>
		<td>名字:</td>
		<td><c:out value="${order.billToFirstName}" /></td>
	</tr>
	<tr>
		<td>姓氏:</td>
		<td><c:out value="${order.billToLastName}" /></td>
	</tr>
	<tr>
		<td>地址 1:</td>
		<td><c:out value="${order.billAddress1}" /></td>
	</tr>
	<tr>
		<td>地址 2:</td>
		<td><c:out value="${order.billAddress2}" /></td>
	</tr>
	<tr>
		<td>城市:</td>
		<td><c:out value="${order.billCity}" /></td>
	</tr>
	<tr>
		<td>省份:</td>
		<td><c:out value="${order.billState}" /></td>
	</tr>
	<tr>
		<td>邮编:</td>
		<td><c:out value="${order.billZip}" /></td>
	</tr>
	<tr>
		<td>国家:</td>
		<td><c:out value="${order.billCountry}" /></td>
	</tr>
	<tr>
		<th colspan="2">邮寄地址</th>
	</tr>
	<tr>
		<td>名字:</td>
		<td><c:out value="${order.shipToFirstName}" /></td>
	</tr>
	<tr>
		<td>姓氏:</td>
		<td><c:out value="${order.shipToLastName}" /></td>
	</tr>
	<tr>
		<td>地址 1:</td>
		<td><c:out value="${order.shipAddress1}" /></td>
	</tr>
	<tr>
		<td>地址 2:</td>
		<td><c:out value="${order.shipAddress2}" /></td>
	</tr>
	<tr>
		<td>城市:</td>
		<td><c:out value="${order.shipCity}" /></td>
	</tr>
	<tr>
		<td>省份:</td>
		<td><c:out value="${order.shipState}" /></td>
	</tr>
	<tr>
		<td>邮编:</td>
		<td><c:out value="${order.shipZip}" /></td>
	</tr>
	<tr>
		<td>国家:</td>
		<td><c:out value="${order.shipCountry}" /></td>
	</tr>
	<tr>
		<td>快递员:</td>
		<td><c:out value="${order.courier}" /></td>
	</tr>
	<tr>
		<td colspan="2">状态: <c:out value="${order.status}" /></td>
	</tr>
	<tr>
		<td colspan="2">
		<table>
			<tr>
				<th>货号</th>
				<th>描述</th>
				<th>数量</th>
				<th>价格</th>
				<th>总计花费</th>
			</tr>
			<c:forEach var="lineItem" items="${order.lineItems}">
				<tr>
					<td><a href="${ctx}/catalog/viewItem?itemId=${lineItem.item.itemId}">
						${lineItem.item.itemId}
					</a></td>
					<td><c:if test="${lineItem.item != null}">
						${lineItem.item.attribute1}
						${lineItem.item.attribute2}
						${lineItem.item.attribute3}
						${lineItem.item.attribute4}
						${lineItem.item.attribute5}
						${lineItem.item.product.name}
					</c:if> <c:if test="${lineItem.item == null}">
						<i>{description unavailable}</i>
					</c:if></td>

					<td>${lineItem.quantity}</td>
					<td><fmt:formatNumber value="${lineItem.unitPrice}"
						pattern="$#,##0.00" /></td>
					<td><fmt:formatNumber value="${lineItem.total}"
						pattern="$#,##0.00" /></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="5">总计: <fmt:formatNumber
					value="${order.totalPrice}" pattern="$#,##0.00" /></th>
			</tr>
		</table>
		</td>
	</tr>

</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
