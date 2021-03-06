<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Welcome">
<div id="WelcomeContent"><c:if
	test="${accountBean != null }">
	<c:if test="${accountBean.authenticated}">
        欢迎 ${accountBean.account.firstName}!
      </c:if>
</c:if></div>
</div>

<div id="Main">
<div id="Sidebar">
<div id="SidebarContent"><a href="${ctx}/catalog/viewCategory?categoryId=FISH">
	<img src="../images/fish_icon.gif" />
</a> <br />
海鱼,淡水鱼 <br />
<a href="${ctx}/catalog/viewCategory?categoryId=DOGS">
	<img src="../images/dogs_icon.gif" />
</a> <br />
宠物犬 <br />
<a href="${ctx}/catalog/viewCategory?categoryId=CATS">
	<img src="../images/cats_icon.gif" />
</a> <br />
宠物猫 <br />
<a href="${ctx}/catalog/viewCategory?categoryId=REPTILES">
	<img src="../images/reptiles_icon.gif" />
</a> <br />
冷血动物 <br />
<a href="${ctx}/catalog/viewCategory?categoryId=BIRDS">
	<img src="../images/birds_icon.gif" />
</a> <br />
鸟类</div>
</div>

<div id="MainImage">
<div id="MainImageContent">
  <map name="estoremap">
	<area alt="Birds" coords="72,2,280,250"
		href="${ctx}/catalog/viewCategory?categoryId=BIRDS" shape="RECT" />
	<area alt="Fish" coords="2,180,72,250"
		href="${ctx}/catalog/viewCategory?categoryId=FISH" shape="RECT" />
	<area alt="Dogs" coords="60,250,130,320"
		href="${ctx}/catalog/viewCategory?categoryId=DOGS" shape="RECT" />
	<area alt="Reptiles" coords="140,270,210,340"
		href="${ctx}/catalog/viewCategory?categoryId=REPTILES" shape="RECT" />
	<area alt="Cats" coords="225,240,295,310"
		href="${ctx}/catalog/viewCategory?categoryId=CATS" shape="RECT" />
	<area alt="Birds" coords="280,180,350,250"
		href="${ctx}/catalog/viewCategory?categoryId=BIRDS" shape="RECT" />
  </map> 
  <img height="355" src="../images/splash.gif" align="middle"
	usemap="#estoremap" width="350" /></div>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

