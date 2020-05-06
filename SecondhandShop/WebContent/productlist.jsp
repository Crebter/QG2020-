<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品</title>
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
		<div class="product-list">
			<h2>${b.getName() }</h2>
			<div class="clear"></div>
			<ul class="product clearfix">
				<c:forEach var="p" items="${sessionScope.product}">
					<li>
						<dl>
							<dt><a href="ProductServlet?method=productdetail&id=${p.getId() }" target="_blank"><img src="/images/product/${p.getPicture() }" /></a></dt>
							<dd class="title"><a href="ProductServlet?method=productdetail&id=${p.getId() }" target="_blank">${p.getName() }</a></dd>
							<dd class="price">￥${p.getPrice() }.00</dd>
						</dl>
					</li>
				</c:forEach>
				
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li>当前:${sessionScope.cpage}/${sessionScope.tpage }页</li>
				    <li><a href="ProductServlet?method=selectBypage&id=${sessionScope.typeid}&cp=1">首页</a></li>
				    <li><a href="ProductServlet?method=selectBypage&id=${sessionScope.typeid}&cp=${sessionScope.cpage-1<=0 ? 1 : sessionScope.cpage-1 }">上一页</a></li>
				    <li><a href="ProductServlet?method=selectBypage&id=${sessionScope.typeid}&cp=${sessionScope.cpage+1>=sessionScope.tpage ? sessionScope.tpage : sessionScope.cpage+1 }">下一页</a></li>
				    <li><a href="ProductServlet?method=selectBypage&id=${sessionScope.typeid}&cp=${sessionScope.tpage }">尾页</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>	
</body>
</html>