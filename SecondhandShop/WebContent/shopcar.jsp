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
	
	
	
	function add(id,stock){
		var va=document.getElementById(id).value;
		if(va>=stock){
			return;
		}
		location.href="OrderServlet?method=shopcarUpdate&pid="+id+"&action=add";
		
	}
	
	
	
	function sub(id){
		var va=document.getElementById(id).value;
		if(va<=1){
			return;
		}
		location.href="OrderServlet?method=shopcarUpdate&pid="+id+"&action=sub";
		
	}
	
	
	
	function closeText(id,stock){
		var va=document.getElementById(id).value;
		if(va<1){
		alert('您输入的数量不能小于1！！！')
			document.getElementById(id).value=1;
			va=1;
		}
		if(va>stock){
		alert('您输入的数量超出库存！！！');
			document.getElementById(id).value=stock;
			va=stock;
		}
		location.href="OrderServlet?method=shopcarUpdate&pid="+id+"&quantity="+va+"&action=close";
	}
	
	
	
	function orderdelete(id){
	if(confirm("确定要删除吗？")) {
		location.href="OrderServlet?method=shopcarUpdate&pid="+id+"&action=delete";
		}
	}
	

	var fxi=0;
	function selectAll(){
	
		var op2 = document.getElementsByName("op2");
		var op =  document.getElementsByName("op");
		
		if(fxi==0){
			for(var i=0;i<op.length;i++)
			{
				op[i].checked=true;
			}
			fxi=1;
		}else{
			for(var i=0;i<op.length;i++)
			{
				op[i].checked=false;
			}
			fxi=0;
		}
		calculate();
	}
	function calculate(){
	var sum=0;
		var op = document.getElementsByName("op");
		var price=document.getElementsByName("priceText");
		var number=document.getElementsByName("number");
		for(var i=0;i<op.length;i++)
		{
			if(op[i].checked)
			{
				sum+=parseInt(parseInt(price[i].innerText)*parseInt(number[i].value));
			}
		}
			
		document.getElementById("totalvalue").innerHTML=sum;	
		document.getElementById("tatalcost").value=sum;
		var money=document.getElementById("totalvalue").innerText;
		if(money=="0"){
			document.getElementById("buybutton").style.display='none';
			document.getElementById("notselect").hidden=false;
		}else{
			document.getElementById("buybutton").style.display='inline';
			document.getElementById("notselect").hidden=true;
			
		}
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


<div class="wrap">
	<div id="shopping">
		<form action="gmServlet">
		
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach var="cars" items="${sessionScope.shopCars}">
					
					<tr id="product_id_1">
						<td class="thumb"><input type="checkbox" name="op" onclick="calculate()" /><img src="/images/product/${cars.getPicture() }" height="100" width="100" />
						<a href="ProductServlet?method=productdetail&id=${cars.getPid()}">${rs.getPname() }</a></td>
						<td class="price" id="price_id_1">
							<span id="priceText" name="priceText" >${cars.getPrice()}</span><!-- 商品单价 --><input type="hidden" name="sPPrice" value="${cars.getPrice()}" />
							<input type="hidden" value="99" />
						</td>
						<td class="number">
							<dl>
								<dt>
								<input type="button"  value="-" onclick="sub(${cars.getId()})" />
								<!-- 购物单id --><input type="hidden" name="esID" value="${cars.getId()}" />
								<!-- 商品id --><input type="hidden" name="spID" value="${cars.getPid()}" />
								<!-- 购买数量 --><input id="${cars.getId()}" type="text" name="number" value="${cars.getQuantity() }"  onblur="closeText(id,${cars.getStock()})" />
								<input type="button"  value="+" onclick="add(${cars.getId()},${cars.getStock()}) " />
								</dt>
								
							</dl>
						</td>
						<td class="delete"><a href="javascript:orderdelete(${cars.getId()});">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<input type="checkbox" onclick="selectAll()" name="op2" />全选 <div align="right" >总价钱:<span id="totalvalue">0</span>元</div>
														<input type="hidden" name="tatalcost" id="tatalcost" value="0" />
			<div align="center"><span id="notselect">请选择物品！！！</span></div>
			<input type="submit" value="结算" id="buybutton" style="color:red; font-size:30px; margin-left:3px; border:0px; brackground:white" />
		</form>
	</div>
</div>	

</body>
</html>