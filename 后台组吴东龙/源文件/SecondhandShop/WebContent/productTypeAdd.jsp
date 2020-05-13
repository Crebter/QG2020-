<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加一个分类</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">

	<div class="help">
		<a href="index.jsp">返回用户界面</a>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${session.user.getId() }您好，欢迎回到管理后台。
	</div>
</div>
<div id="main" class="wrap">
	<div class="main">
		<h2>添加分类</h2>
		<div class="manage">
			<form action="ProductServlet?method=doAddSmall" method="post">
				<table class="form">
					<tr>
						<td class="field">父分类：</td>
						<td>
							<select name="parentId">
								<option value="0" selected="selected">根栏目</option>
								<c:forEach var="OB" items="${sessionScope.Blist}" varStatus="sta" >
										<option value="${OB.getId() }">${OB.getName() }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">分类名称：</td>
						<td><input type="text" class="text" name="name"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="提交" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>