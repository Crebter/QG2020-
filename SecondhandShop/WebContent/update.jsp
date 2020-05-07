<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>猿来入此-二手物品商城 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript">
function selectname(){
	  var name = document.getElementById("selectname").value;
	  location.href='ProductServlet?method=selectBypage&name='+name;
}
function searchHot(name){
	location.href='ProductServlet?method=selectBypage&name='+name;
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
		<c:if test="${sessionScope.user!=null}">
			欢迎您:${sessionScope.user.getId() }
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
		<c:if test="${sessionScope.user!=null}">
			<a href="selectdd?dd=${user.getId() }">个人订单</a>
		</c:if>
			<a href="OrderServlet?method=shopcarselectAll" class="shopping">购物车</a>
		<c:if test="${sessionScope.user == null}">
			<a href="login.jsp">登录</a>|<a href="register.jsp">注册</a>
		</c:if>
		<c:if test="${sessionScope.user!=null}">
			<a href="UserServlet?method=exit">退出登录</a>
		</c:if>
			<a href="SelallServlet">留言</a>
		<c:if test="${sessionScope.user.getStatus() == 2}">
			<a href="manage/index.jsp" >去后台</a>
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
			<h1 align="center">您的个人信息</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写修改信息</li>
				<li class="last"><em></em>修改成功</li>
			</ul>
			<form  method="post" action="UserServlet?method=update" >
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="userName" value="${sessionScope.user.getId() }" readonly/><span></span></td>
					</tr>
					<tr>
						<td class="field">姓名：</td>
						<td><input type="text" class="text" value="${sessionScope.user.getUsername() }" name="name" /></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="passWord" value="${sessionScope.user.getPassword() }" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td><input name="sex" value="${sessionScope.user.getSex() }"  readonly/>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td><input type="text" class="text" name="birthday" value="${sessionScope.user.getBirthday() }" onfocus="FocusItem(this)" onblur="CheckItem(this)" /><span></span></td>
					</tr>
					<tr>
						<td class="field">身份证号：</td>
						<td><input type="text" class="text" name="card" value="${sessionScope.user.getCard() }" onfocus="FocusItem(this)" onblur="CheckItem(this);" readonly/><span></span></td>
					</tr>
					<tr>
						<td class="field">邮箱：</td>
						<td><input type="text" class="text" name="email" value="${sessionScope.user.getEmail() }" onfocus="FocusItem(this)" onblur="CheckItem(this);" readonly/><span></span></td>
					</tr>
					<tr>
						<td class="field">手机号：</td>
						<td><input class="text" type="text" name="phone" value="${sessionScope.user.getPhone() }" onfocus="FocusItem(this)" onblur="CheckItem(this)" /><span></span></td>
					</tr>
					<tr>
						<td class="field">家庭地址：</td>
						<td><input type="text" class="text" name="uaddress" value="${sessionScope.user.getUaddress() }" onfocus="FocusItem(this)" onblur="CheckItem(this);"/><span></span></td>
					</tr>
					<tr>
						<td class="field">送货地址：</td>
						<td><input type="text" class="text" name="paddress" value="${sessionScope.user.getPaddress() }" onfocus="FocusItem(this)" onblur="CheckItem(this);"/><span></span></td>
					</tr>
					<tr>
						<td><input type="hidden" class="text" name="status" value="${sessionScope.user.getStatus() }" onfocus="FocusItem(this)" onblur="CheckItem(this);" readonly/><span></span></td>
					</tr>
					<tr>
						<td><a href="ProductServlet?method=selectAll">返回</a></td>
						<td><label class="ui-green"><input type="submit" name="submit"  value="提交修改" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>