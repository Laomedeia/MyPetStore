<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<form method="post" action="${ctx}/account/editAccount">

	<h3>用户信息(User Information)</h3>

	<table>
		<tr>
			<td>用户ID:</td>
			<td>${accountBean.account.username}<input type="hidden" name="username" value="${accountBean.account.username}"/></td>
		</tr>
		<tr>
			<td>新密码:</td>
			<td><input type="password" name="password" value="${accountBean.account.password}" /></td>
		</tr>
		<tr>
			<td>确认密码:</td>
			<td><input type="password" name="repeatedPassword" /></td>
		</tr>
	</table>
	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="editAccount" value="保存账号信息" />

</form> 
<a href="${ctx}/order/listOrders">我的订单</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
