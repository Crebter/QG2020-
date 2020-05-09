<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改订单</title>
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
		<h2 style="font-size:20px;" >修改订单</h2>
		<div class="manage">
			<form action="OrderServlet?method=doUpdate" method="post" >
				<table class="form">
					<tr>
						<td class="field">订单号:</td>
						<td><input type="text" class="text" name="id"  value="${sessionScope.oldShopOrder.getId() }" readonly/></td>
						<td><input type="hidden" class="text" name="uid"  value="${sessionScope.oldShopOrder.getUid() }"/></td>
						<td><input type="hidden" class="text" name="seller"  value="${sessionScope.oldShopOrder.getSeller() }"/></td>	
						<td><input type="hidden" class="text" name="type"  value="${sessionScope.oldShopOrder.getType() }"/></td>	
						<td><input type="hidden" class="text" name="status"  value="${sessionScope.oldShopOrder.getStatus() }"/></td>	
					</tr>
					<tr>
						<td class="field">商品号:</td>
						<td><input type="text" class="text" name="pid"  value="${sessionScope.oldShopOrder.getPid() }" readonly/></td>
					</tr>
					<tr>
						<td class="field">买家:</td>
						<td><input type="text" class="text" name="uid" value="${sessionScope.oldShopOrder.getUid() }" readonly/></td>
					</tr>
					<tr>
						<td class="field">送货地址:</td>
						<td><input type="text" class="text" name="uaddress" value="${sessionScope.oldShopOrder.getUaddress() }" /></td>
					</tr>
					<tr>
						<td class="field">购买时间:</td>
						<td><input type="text" class="text" name="createtime" value="${sessionScope.oldShopOrder.getCreatetime() }"readonly/></td>
					</tr>
					<tr>
						<td class="field">总花费</td>
						<td><input type="text" class="text" name="cost" value="${sessionScope.oldShopOrder.getCost() }"/> 元(整数)</td>
					</tr>
					<tr>
						<td class="field">购买数量:</td>
						<td><input type="text" class="text" name="quantity" value="${sessionScope.oldShopOrder.getQuantity() }"/></td>
					</tr>
					<tr>
						<td><a href="OrderServlet?method=Sold&uid=${user.getId() }">返回</a></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="确认修改" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>	
</body>
</html>