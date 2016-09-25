<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink"><a href="${ctx}/catalog/viewMain">
	回到首页
	</a></div>

<div id="Catalog">请确认以下订单信息后点击"确定"按钮

<table>
	<tr>
		<th align="center" colspan="2"><font size="4"><b>订单</b></font>
		<br />
		<font size="3"><b> <fmt:formatDate
			value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></b></font>
		</th>
	</tr>

	<tr>
		<th colspan="2">账单地址</th>
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

</table>

<a class="Button" href="${ctx}/order/newOrder?confirmed=true&orderSid=${orderSid}">
确定
</a></div>

<%@ include file="../common/IncludeBottom.jsp"%>





