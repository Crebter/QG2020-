<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
<title>卖出去的宝贝</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script language="javascript">

function selectname(){
	  var name = document.getElementById("selectname").value;
	  location.href='ProductServlet?method=selectBypage&name='+name;
}
function searchHot(name){
	location.href='ProductServlet?method=selectBypage&name='+name;
}

function base64 (content) {
    return window.btoa(unescape(encodeURIComponent(content)));         
 }
 /*
 *@tableId: table的Id
 *@fileName: 要生成excel文件的名字（不包括后缀，可随意填写）
 */
 function tableToExcel(tableID,fileName){
     var table = document.getElementById(tableID);
   var excelContent = table.innerHTML;
   var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
   excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
   excelFile += "<body><table>";
   excelFile += excelContent;
   excelFile += "</table></body>";
   excelFile += "</html>";
   var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
   var a = document.createElement("a");
   a.download = fileName+".xls";
   a.href = link;
   a.click();
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
			<h2 style="font-size:20px;" align="center" >请您确认一下订单</h2>
			<div class="manage">
				<table class="list" id="soldWaiting">
					<tr>
						<th>订单号</th>
						<th>商品号</th>
						<th>送货地址</th>
						<th>购买时间</th>
						<th>购买件数</th>
						<th>买家</th>
						<th>总花费</th>
						<th>允许买</th>
						<th>拒绝买</th>
					</tr>
					<c:forEach var="W" items="${sessionScope.soldWaiting}"> 
						<tr>
							<td class="first w4 c">${W.getId() }</td>
							<td class="first w4 c"><a href="ProductServlet?method=productdetail&id=${W.getPid() }" target="_blank">${W.getPid() }</a></td>
							<td class="first w4 c">${W.getUaddress() }</td>
							<td class="first w4 c">${W.getCreatetime() }</td>
							<td class="first w4 c">${W.getQuantity() }</td>
							<td class="first w4 c">${W.getUid() }</td>				
							<td class="first w4 c">${W.getCost() }</td>
							<td class="w1 c"><a href="OrderServlet?method=allow&id=${W.getId() }">允许</a></td>
							<td class="w1 c"><a href="OrderServlet?method=refuse&id=${W.getId() }">拒绝</a></td>
						</tr>
					</c:forEach>
				</table>
				<button type="button" onclick="tableToExcel('soldWaiting','待确认的订单')">导出</button>
				<br><br><br>
				
			<h2 style="font-size:20px;" align="center" >允许买家购买的订单</h2>
				<table class="list" id="soldYes">
					<tr>
						<th>订单号</th>
						<th>商品号</th>
						<th>送货地址</th>
						<th>购买时间</th>
						<th>购买件数</th>
						<th>买家</th>
						<th>总花费</th>
						<th>操作</th>
					</tr>
					<c:forEach var="Y" items="${sessionScope.soldYes}"> 
						<tr>
							<td class="first w4 c">${Y.getId() }</td>
							<td class="first w4 c"><a href="ProductServlet?method=productdetail&id=${Y.getPid() }" target="_blank">${Y.getPid() }</a></td>
							<td class="first w4 c">${Y.getUaddress() }</td>
							<td class="first w4 c">${Y.getCreatetime() }</td>
							<td class="first w4 c">${Y.getQuantity() }</td>
							<td class="first w4 c">${Y.getUid() }</td>				
							<td class="first w4 c">${Y.getCost() }</td>			<!-- 跳转页面，servlet里面设置type=1 -->
							<td class="w1 c"><a href="OrderServlet?method=update&id=${Y.getId() }">修改订单</a> </td>
						</tr>
					</c:forEach>
				</table>
				<button type="button" onclick="tableToExcel('soldYes','允许买家购买的订单')">导出</button>
				<br><br><br>
				
	
			<h2 style="font-size:20px;" align="center" >不允许买家购买的订单</h2>
				<table class="list" id="soldNo">
					<tr>
						<th>订单号</th>
						<th>商品号</th>
						<th>送货地址</th>
						<th>购买时间</th>
						<th>购买件数</th>
						<th>买家</th>
						<th>总花费</th>
						<th>状态</th>
					</tr>
					<c:forEach var="N" items="${sessionScope.soldNo}"> 
						<tr>
							<td class="first w4 c">${N.getId() }</td>
							<td class="first w4 c"><a href="ProductServlet?method=productdetail&id=${N.getPid() }" target="_blank">${N.getPid() }</a></td>
							<td class="first w4 c">${N.getUaddress() }</td>
							<td class="first w4 c">${N.getCreatetime() }</td>
							<td class="first w4 c">${N.getQuantity() }</td>
							<td class="first w4 c">${N.getUid() }</td>
							<td class="first w4 c">${N.getCost() }</td>
							<td class="first w4 c">拒绝</td>
						</tr>	
					</c:forEach>
				</table>
				<button type="button" onclick="tableToExcel('soldNo','不允许买家购买的订单')">导出</button>
				<br><br><br>
				
			</div>
		</div>	
</div>		


</body>
</html>