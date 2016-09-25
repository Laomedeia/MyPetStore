<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<form method="post" action="${ctx}/account/newAccount">

	<h3>用户信息(User Information)</h3>

	<table>
		<tr>
			<td>用户ID:</td>
			<td><input type="text" name="username" /></td>
		</tr>
		<tr>
			<td>新密码:</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>确认密码:</td>
			<td><input type="password" name="repeatedPassword" /></td>
		</tr>
	</table>

	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="newAccount" value="保存新账号" />

</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>