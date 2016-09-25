<%@ page contentType="text/html;charset=UTF-8" language="java"%>

</div>

<div id="Footer">

<div id="PoweredBy">&nbsp;<a href="http://www.mystoredemo.com">我的宠物店</a>
</div>

<div id="Banner"><c:if test="${accountBean != null }">
	<c:if test="${accountBean.authenticated}">
		<c:if test="${accountBean.account.bannerOption}">
          ${accountBean.account.bannerName}
        </c:if>
	</c:if>
</c:if></div>

</div>

</body>
</html>