<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>猿来入此-二手物品商城- 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript">
function selectname(){
	  var name = document.getElementById("selectname").value;
	  location.href='selectProductList?name='+name;
}
function searchHot(name){
	location.href='selectProductList?name='+name;
}
/*
 * 切换验证码
 */
function change(){
	var c = document.getElementById("veryCode");
	c.src="CodeServlet?num="+Math.random();
}
</script>
</head>
<body>
<div id="header" class="wrap">
	<div class="help">
		<c:if test="${SessionScope.user!=null}">
				<a href="selectdd?dd=${SessionScope.user.id }">个人订单</a>
		</c:if>
		
		<c:if test="${SessionScope.user!=null}">当前用户${SessionScope.user.id }
		</c:if>
			<a href="OrderServlet?method=shopcarselectAll" class="shopping">购物车</a>
		<c:if test="${SessionScope.user==null}">
				<a href="login.jsp">登录</a>|
				<a href="register.jsp">注册</a>
		</c:if>
		
		<c:if test="${SessionScope.user!=null}">
				<a href="zx">退出</a>
		</c:if>
		
		<a href="SelallServlet">留言</a>
		
		<c:if test="${SessionScope.user.status==2}">
				<a href="manage/index.jsp" >去后台</a>
		</c:if>
	</div>
	
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="ProductServlet?method=selectAll">首页</a></li>
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



<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>密码找回</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写您的个人信息</li>
				<li class="last"><em></em>修改成功</li>
			</ul>
			<form  method="post" action="UserServlet?method=check" >
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="userName" onfocus="FocusItem(this)" onblur="CheckItem(this)" /><span></span></td>
					</tr>
					<tr>
						<td class="field">身份证号：</td>
						<td><input type="text" class="text" name="card" value="" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">邮箱：</td>
						<td><input type="text" class="text" name="email" value="" onfocus="FocusItem(this)" onblur="CheckItem(this);"/><span></span></td>
					</tr>
					<tr>
						<td class="field">新密码：</td>
						<td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" name="rePassWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="Checknum(this);" /><img id="veryCode" alt="看不清,换一张" style="cursor:hand;" src="CodeServlet" onclick="change()" /><span id="msg"></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit"  value="更改密码" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>


</body>
</html>