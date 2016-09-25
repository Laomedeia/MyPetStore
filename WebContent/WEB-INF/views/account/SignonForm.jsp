<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<form method="post" action="${ctx}/account/signon">

	<p>请输入用户名和密码</p>
	<p>用户名 &nbsp;&nbsp;<input type="text" name="username" value="j2ee" /> <br /><br />
	密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password" value="j2ee" /></p>
	<input type="submit" name="signon" value="登录" />

</form> 没有登录用户? 
<a href="${ctx}/account/newAccountForm">现在注册!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

