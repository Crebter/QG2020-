<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QG闲鱼网后台</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">

	<div class="help">
		<a href="ProductServlet?method=selectAll">返回用户界面</a>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${session.user.getId() }您好，欢迎回到管理后台。
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
			<h2 style="font-size:20px;" align="center" >用户管理</h2>
			<div class="manage">
				<table class="list" >
					<tr>
						<th>用户名</th>
						<th>姓名</th>
						<th>性别</th>
						<th>身份证</th>
						<th>邮箱</th>
						<th>电话</th>
						<th>家庭住址</th>
						<th>操作</th>
					</tr>
					<c:forEach var="U" items="${sessionScope.allUsers}"> 
						<tr>
							<td class="first w4 c">${U.getId() }</td>
							<td class="first w4 c">${U.getUsername() }</td>
							<td class="first w4 c">${U.getSex() }</td>
							<td class="first w4 c">${U.getCard() }</td>
							<td class="first w4 c">${U.getEmail() }</td>
							<td class="first w4 c">${U.getPhone() }</td>				
							<td class="first w4 c">${U.getUaddress() }</td>
							<td class="w1 c"><a href="UserServlet?method=deleteById&id=${U.getId() }">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				<br><br><br>
				
			<h2 style="font-size:20px;" align="center" >商品分类</h2>
				<table class="list" >
					<tr>
						<th>父类</th>
					</tr>
					<c:forEach var="B" items="${sessionScope.blist}"> 
						<tr>
							<td class="first w4 c">${B.getName() }</td>
						</tr>
					</c:forEach>
				</table>
				<table class="list" >
					<tr>
						<th>子类</th>
						<th>新增</th>
						<th>删除</th>
					</tr>
					<c:forEach var="S" items="${sessionScope.slist}"> 
						<tr>
							<td class="first w4 c">${S.getName() }</td>
							<td class="w1 c"><a href="ProductServlet?method=addSmall">新增</a></td>
							<td class="w1 c"><a href="ProductServlet?method=deleteSmall&id=${S.getId() }">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				
				<br><br><br>
				
	
			<h2 style="font-size:20px;" align="center" >审核买家上传的商品</h2>
				<table class="list" >
					<tr>
						<th>名字</th>
						<th>简介</th>
						<th>价格</th>
						<th>库存</th>
						<th>照片</th>
						<th>拥有者</th>
						<th>允许</th>
						<th>拒绝</th>
					</tr>
					<c:forEach var="p" items="${sessionScope.products}"> 
						<tr>
							<td class="first w4 c"> <a href="ProductServlet?method=productdetail&id=${p.getId() }">${p.getName() }</a></td>
							<td class="first w4 c">${p.getIntroduction() }</td>
							<td class="first w4 c">${p.getPrice() }</td>
							<td class="first w4 c">${p.getStock() }</td>
							<td class="first w4 c">${p.getPicture() }</td>
							<td class="first w4 c">${p.getUid() }</td>
							<td class="w1 c"><a href="ProductServlet?method=updateValid&id=${p.getId() }&valid=1">允许</a></td>
							<td class="w1 c"><a href="ProductServlet?method=refuse&id=${p.getId() }">拒绝</a></td>	
						</tr>	
					</c:forEach>
				</table>
				<br><br><br>
				
			<h2 style="font-size:20px;" align="center" >投诉箱处理</h2>
				<table class="list" >
					<tr>
						<th>投诉单</th>
						<th>投诉人</th>
						<th>投诉内容</th>
						<th>投诉时间</th>
						<th>状态</th>
						<th>删除</th>

					</tr>
					<c:forEach var="com" items="${sessionScope.Allcomplains}"> 
						<tr>
							<td class="first w4 c"> ${com.getId() }</td>
							<td class="first w4 c">${com.getNickname() }</td>
							<td class="first w4 c">${com.getContent() }</td>
							<td class="first w4 c">${com.getContenttime() }</td>
							<td class="first w4 c">
								<c:if test="${com.getReply() == null}">
									<a href="ComplainServlet?method=update&id=${com.getId() }">回复</a>
								</c:if>
								<c:if test="${com.getReply() != null}">
									已回复
								</c:if>
							</td>
							<td class="first w4 c"><a href="ComplainServlet?method=delete&id=${com.getId() }">删除</a></td>
						</tr>	
					</c:forEach>
				</table>
				<br><br><br>
				
				
				
				


			</div>
	</div>	
</div>		

</body>
</html>