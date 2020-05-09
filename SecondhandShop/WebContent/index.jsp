<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QG闲鱼网首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script language="javascript">

function selectname(){
	  var name = document.getElementById("selectname").value;
	  location.href='ProductServlet?method=selectBypage&name='+name;
}
function searchHot(name){
	location.href='ProductServlet?method=selectBypage&name='+name;
}
</script>


</head>
<body>
<div id="header" class="wrap">
	<div class="help">
		<c:if test="${sessionScope.user!=null}">
			欢迎您:${sessionScope.user.getId() }
		</c:if>
		<c:if test="${sessionScope.user==null}">
			你现在的身份是:游客
		</c:if>	
		<c:if test="${sessionScope.user!=null}" >
			<a href="OrderServlet?method=Sold&uid=${user.getId() }" >卖出的宝贝</a>
		</c:if>
		<c:if test="${sessionScope.user!=null}" >
			<a href="OrderServlet?method=Bought&uid=${user.getId() }" >买到的宝贝</a>
		</c:if>
		<c:if test="${sessionScope.user!=null}" >
			<a href="productAdd.jsp" >上传闲置物品</a>
		</c:if>
		<c:if test="${sessionScope.user!=null}">
			<a href="ProductServlet?method=myProduct&uid=${user.getId() }">我的商品</a>
		</c:if>
		<c:if test="${sessionScope.user!=null}" >
			<a href="update.jsp" >个人信息</a>
		</c:if>
			<a href="OrderServlet?method=shopcarselectAll" class="shopping">购物车</a>
		<c:if test="${sessionScope.user == null}">
			<a href="login.jsp">登录</a>|<a href="register.jsp">注册</a>
		</c:if>
		<c:if test="${sessionScope.user!=null}">
			<a href="UserServlet?method=exit">退出登录</a>
		</c:if>
			<a href="ComplainServlet?method=select">投诉箱</a>
		<c:if test="${sessionScope.user.getStatus() != 1 && user != null}">
			<a href="UserServlet?method=admin" >回到后台</a>
		</c:if>
	</div>
	
	<div class="navbar">
		<ul class="clearfix">
		<!-- 跟首页同一行的大类的展示 -->
			<li class="current" ><a href="ProductServlet?method=selectAll">首页</a></li>
			<c:forEach var="b" items="${sessionScope.blist}">
			<li><a href="ProductServlet?method=selectBypage&id=${b.getId() }">${b.getName() }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>



<!-- 按字段查询商品 -->
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
		<c:forEach var="s" items="${sessionScope.slist }">
			<li class="first"><a href="javascript:searchHot('${s.getName() }')">${s.getName() }</a></li>
		</c:forEach>
			<li class="last"><input type="text" id="selectname" value="${search_words }" /><a href="javascript:selectname()" >&nbsp;&nbsp;搜索</a></li>
		</ul>
	</div>
</div>



<div id="main" class="wrap">
	<div class="lefter">
		<div class="spacer"></div>
		<div class="last-view">
			<h2 style="font-size:20px;" align="center">最近浏览</h2>
			<dl class="clearfix">
				<c:forEach var="lastp" items="${sessionScope.lastlylist}">
					<dt><a href="ProductServlet?method=productdetail&id=${lastp.getId() }"><img height="40" src="/images/product/${lastp.getPicture() }" /></a></dt>
					<dd><a href="ProductServlet?method=productdetail&id=${lastp.getId() }">${lastp.getName() }</a></dd>
				</c:forEach>
			</dl>
		</div>
	</div>
	
	


	<div class="main">
		<div class="price-off">
			<h2 style="font-size:20px;" align="center" >今日低价</h2>
			<ul class="product clearfix">
				<c:forEach var="tp" items="${sessionScope.tlist}">
					<li>
						<dl>
							<dt><a href="ProductServlet?method=productdetail&id=${tp.getId() }" target="_blank"><img src="/images/product/${tp.getPicture() }" /></a></dt>
							<dd class="title"><a href="ProductServlet?method=productdetail&id=${tp.getId() }" target="_blank">${tp.getIntroduction() }</a></dd>
							<dd class="price">${tp.getPrice() }.00</dd>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
		
		<div class="spacer clear"></div>
		<div class="hot">
			<h2 style="font-size:20px;" align="center" >商品库存排行</h2>
			<ul class="product clearfix">
					<c:forEach var="st" items="${sessionScope.stocklist}">
					<li>
						<dl>
							<dt><a href="ProductServlet?method=productdetail&id=${st.getId() }" target="_blank"><img src="/images/product/${st.getPicture() }" /></a></dt>
							<dd class="title"><a href="ProductServlet?method=productdetail&id=${st.getId() }" target="_blank">${st.getIntroduction() }</a></dd>
							<dd class="price">${st.getPrice() }.00</dd>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>	
</body>
</html>