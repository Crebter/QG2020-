<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我发布的商品</title>
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
			<h2 style="font-size:20px;" align="center" >审核通过的商品</h2>
			<div class="manage">
				<table class="list">
					<tr>
						<th>ID</th>
						<th>商品名称</th>
						<th>操作</th>
					</tr>
					<c:forEach var="v1" items="${sessionScope.valid1}"> 
						<tr>
							<td class="first w4 c">${v1.getId() }</td>
							<td class="thumb"><img src="/images/product/${v1.getPicture() }" width="80" height="80"/><a href="ProductServlet?method=productdetail&id=${v1.getId() }" target="_blank">${v1.getName() }</a></td>
							<td class="w1 c"><a href="ProductServlet?method=updateshow&id=${v1.getId() }">修改</a> <a href="ProductServlet?method=delete&id=${v1.getId() }&uid=${sessionScope.user.getId()}">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				<br><br><br>
				
			<h2 style="font-size:20px;" align="center" >审核未通过的商品</h2>
				<table class="list">
					<tr>
						<th>ID</th>
						<th>商品名称</th>
						<th>拒绝理由</th>
						<th>操作</th>
					</tr>
					<c:forEach var="v2" items="${sessionScope.valid2}"> 
						<tr>
							<td class="first w4 c">${v2.getId() }</td>
							<td class="thumb"><img src="/images/product/${v2.getPicture() }" width="80" height="80"/><a href="ProductServlet?method=productdetail&id=${v2.getId() }" target="_blank">${v2.getName() }</a></td>
							<td>${v2.getReason() }</td>
							<td><a href="ProductServlet?method=delete&id=${v2.getId() }&uid=${sessionScope.user.getId()}">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				<br><br><br>
	
			<h2 style="font-size:20px;" align="center" >审核中的商品</h2>
				<table class="list">
					<tr>
						<th>ID</th>
						<th>商品名称</th>
						<th>操作</th>
					</tr>
					<c:forEach var="v3" items="${sessionScope.valid3}"> 
						<tr>
							<td class="first w4 c">${v3.getId() }</td>
							<td class="thumb"><img src="/images/product/${v3.getPicture() }" width="80" height="80"/><a href="ProductServlet?method=productdetail&id=${v3.getId() }" target="_blank">${v3.getName() }</a></td>
							<td class="w1 c"><a href="ProductServlet?method=updateshow&id=${v3.getId() }">修改</a> <a href="ProductServlet?method=delete&id=${v3.getId() }&uid=${sessionScope.user.getId()}">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				
			</div>
		</div>	
</div>		


</body>
</html>