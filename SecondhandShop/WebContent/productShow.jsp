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
  function jump(p){
	  location.href='ProductServlet?method=selectBypage&cp='+p;
  }

	function selectname(){
		  var name = document.getElementById("selectname").value;
		  location.href='ProductServlet?method=selectBypage&name='+name;
	}
	
	function searchHot(name){
		location.href='ProductServlet?method=selectBypage&name='+name;
	}
	
	function sub(){
		var count = parseInt(document.getElementById("count").value);
		if(count>0){
			count--;
		}
		if(count==0){
			count=1;
		}
		document.getElementById("count").value = count;
	}
	
	function add(){
		var count = parseInt(document.getElementById("count").value);
		var stock = parseInt(document.getElementById("stock").innerHTML);
		if(count<stock){
			count++;
		}
		document.getElementById("count").value = count;
	}
	
	function checkcount(){
		var count = parseInt(document.getElementById("count").value);
		var stock = parseInt(document.getElementById("stock").innerHTML);
		if(count>stock){
			alert('库存不足');
			document.getElementById("count").value = stock;
		}
		if(count==0){
			alert('购买数量不能小于1哦,亲');
			document.getElementById("count").value = 1;
		}
		if(count<0){
			alert('数量非法');
			document.getElementById("count").value = 1;
		}
	}
	
	function shopAdd(idd){
		var id = idd;
		var count = parseInt(document.getElementById("count").value);
		location.href='OrderServlet?method=addShopCar&id='+id+'&count='+count;
	}
	
	function buynow(idd){
		var id = idd;
		shopAdd(idd);
	}
</script>


<style type="text/css">

#magnifier{
	width:300px;
	height:300px;
	position:absolute;
	/*top:200px;
	left:400px;*/
	font-size:0;
	border:1px solid #000;
}
#img{
	width:300px;
	height:300px;
}

#mag{
	border:1px solid #000;
	overflow:hidden;
	z-index:100;
}
</style>
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


	<div id="product" class="main">
		<h1 style="color:red;">${sessionScope.productDetail.getName() }</h1>
		<div class="infos">
		<div id="magnifier">
			<img src="/images/product/${sessionScope.productDetail.getPicture() }" id="img"/>
		</div>
		<div id="mag"><img id="magnifierImg" /></div>

			 
			<div class="buy">
				<p>商城价：<span class="price">￥${sessionScope.productDetail.getPrice() }.00</span></p>
				<p>邮递方式：<font color="red">QG快递</font></p>
				<p>热卖程度：☆☆☆☆☆</p>
				<p>库　存：<span id="stock">${sessionScope.productDetail.getStock() }</span></p>
				<p>购买数量：<input type="button" value="  -  " onclick="sub()"/><input align="middle" type="text" value="1" id="count" name="count" onblur="checkcount()"/><input type="button" value="  +  " onclick="add()"/></p>
				<div class="button"><input type="button" name="button" value="购买商品" style="color:blue; font-size:17px;" onclick="buynow(${sessionScope.productDetail.getId()})" /><a href="javascript:shopAdd(${sessionScope.productDetail.getId()})">放入购物车</a></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2 style="color:red;"><strong>商品详情</strong></h2>
			<div class="text">
				${sessionScope.productDetail.getIntroduction() }<br />
			</div>
		</div>
	</div>	
</div>

</body>
</html>