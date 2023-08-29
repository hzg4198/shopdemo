<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<%--<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />--%>

<%--<script language="javascript"--%>
<%--	src="${pageContext.request.contextPath}/js/public.js"></script>--%>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
			}
</script>
	<style>
		#completeShow{
			border: 1px solid #999;
			min-height: 200px;
			position: absolute;
			width: 190px;
			z-index: 100;
			background-color: #fff;
			left: 1030px;
			display: none
		}
	</style>

</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
<%--		action="${pageContext.request.contextPath}/user/list.jsp"--%>
			action="${pageContext.request.contextPath}/ProductServlet"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0" >
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
<%--						<form href="${pageContext.request.contextPath}/ProductServlet" >--%>
							商品分类：<select name="cid" id="categorySelect" >
									<option value="">---------</option>
									<c:forEach items="${requestScope.categoryList}" var="items">
										<option value="${items.cid}">${items.cname}</option>
									</c:forEach>
								</select>
						商品名称:<input type="text" id="search" name="word" value="${pname}"> <input  class="btn btn-primary"  id="queryKeyWord" type="submit" value="查询">
						<div id="completeShow">
							<ul id="itemul" class="list-group">
								<!-- 		<li class="list-group-item">aaaa</li>	  -->
							</ul>
						</div>
<%--						</form>--%>
						<button  class="btn btn-primary"  type="button" id="delete" name="delete" >删除所选</button>
						<button  class="btn btn-primary"  type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1" class="table-bordered"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="5%"><input type="checkbox" id="selectAll">全选</td>
								<td align="center" width="9%">序号</td>
								<td align="center" width="17%">商品图片</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="17%">商品价格</td>
								<td align="center" width="17%">是否热门</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
							</tr>
									<c:forEach items="${pageBean.data }" var="product">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">

											<td  align="center" width="5%"><input type="checkbox" value="${product.pid}" class="productBox"> </td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="9%">${product.pid}</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%"><img width="40" height="45" src="${pageContext.request.contextPath}/${product.pimage}"></td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%" class="gaoliang">${product.pname}</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">${product.market_price}</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">${product.is_hot==1?"是":"否"}</td>
											<td align="center" style="HEIGHT: 22px"><a
													href="${ pageContext.request.contextPath }/admin/product/edit.jsp?id=${product.pid}">
												<img
														src="${pageContext.request.contextPath}/images/i_edit.gif"
														border="0" style="CURSOR: hand">
											</a></td>

											<td align="center" style="HEIGHT: 22px"><a href="${pageContext.request.contextPath}/DeleteServlet?id=${product.pid}" id="delete-link"> <img
													src="${pageContext.request.contextPath}/images/i_del.gif"
													width="16" height="16" border="0" style="CURSOR: hand" onclick="return confirm('确认要删除吗')">
											</a></td>
										</tr>
									</c:forEach>

						</table>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item ${pageBean.pageNumber-1 == 0 ? 'disabled' : ''}">
				<a class="page-link" href="${pageContext.request.contextPath }/ProductServlet?pageNumber=${pageBean.pageNumber-1 }&&cid=${cid}&&word=${pname}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
			<c:forEach begin="1" end="${pageBean.totalPage }" var="i">
				<li class="page-item ${i == pageBean.pageNumber ? 'active' : ''}"><a class="page-link" href="${pageContext.request.contextPath }/ProductServlet?pageNumber=${i }&&cid=${cid}&&word=${pname}">${i}</a></li>
			</c:forEach>

			<li class="page-item ${pageBean.pageNumber+1 > pageBean.totalPage ? 'disabled' : ''}">
				<a class="page-link" href="${pageContext.request.contextPath }/ProductServlet?pageNumber=${pageBean.pageNumber +  }&&cid=${cid}&&word=${pname}" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
</body>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"> </script>
<script>

	<%--$("ul.pagination li.page-item a.page-link").each(function() {--%>
	<%--	var modifiedEleval = encodeURIComponent($("#search").val());--%>
	<%--	var modifiedCategorySelectVal = encodeURIComponent($("#categorySelect").val());--%>

	<%--	var pageNumber = $(this).text();--%>
	<%--	var updatedHref = "${pageContext.request.contextPath}/ProductServlet?pageNumber=" + pageNumber + "&&cid=" + modifiedCategorySelectVal + "&&word=" + modifiedEleval;--%>
	<%--	console.log(updatedHref)--%>
	<%--	$(this).attr("href", updatedHref);--%>
	<%--});--%>


	function searchWord() {
		var selectCid = $("#categorySelect").val();
		var keyWord = $("#search").val();

		$.ajax({
			url:"/shop/SearchKeyWordServlet",
			type:"POST",
			data:{
				"cid":selectCid,
				"keyWord":keyWord
			},

			success:function(data){
				$("#itemul").empty();
				var keyWord = $("#search").val();
				var keyWordRegExp = new RegExp(keyWord, 'i'); // 不区分大小写的正则表达式
				for(var i=0;i<data.length;i++){
					var product=data[i];
					var str = product.pname;
					var highlighted = str.replace(keyWordRegExp, function(match) {
						return "<span style='color: blue;'>" + match + "</span>";
					});
					$("#itemul").append("<li class='list-group-item list-group-item-action' onclick='shangping(this.textContent)'>"+highlighted+"</li>")
				}
				if(keyWord !== null && keyWord !== ""){
					$("#completeShow").show();
				}else{
					$("#completeShow").hide();
				}
			},
			error:function(){
				alert('请求失败')
			}
		})

	}

	function shangping(eleval) {
		//将+号替换为URL编码形式
		var modifiedEleval = eleval.replace(/\+/g, '%2B');
		console.log(modifiedEleval);
		// 使用经过处理的值来显示在搜索框中
		$("#search").text(modifiedEleval);
		$("#completeShow").hide();
		// 对categorySelect的值进行编码
		var categorySelectVal = encodeURIComponent($("#categorySelect").val());
		location.href = "/shop/ProductServlet?word=" + modifiedEleval + "&&cid=" + categorySelectVal;
	};

	$(".gaoliang").each(function() {

		var pname = $(this).text();
		var keyWord = $("#search").val();
		var keyWordRegExp = new RegExp(keyWord, 'i'); // 不区分大小写的正则表达式

		/*var startIndex = pname.indexOf(inputValue);
		var endIndex = startIndex + inputValue.length;

		var highlighted = pname.substring(0, startIndex) +
				"<span style='color: blue;'>" + inputValue + "</span>" +
				pname.substring(endIndex);*/

		var highlighted = pname.replace(keyWordRegExp, function(match) {
			return "<span style='color: blue;'>" + match + "</span>";
		});

		$(this).html(highlighted);

	});
	// $("#queryKeyWord").click(searchWord)
	$("#search").keyup(searchWord)

	var list = "${pageBean.data}"
	if(list=='null'||list==""){
		location.href="/shop/ProductServlet";
	}

	var success = '${param.success}'
	if(success !=null && success !==''){
		if(success == 'true'){
			alert("删除成功")
		}else alert("删除失败")
	}
	var productBoxes = $(".productBox");
	$("#selectAll").click(function () {
		$(".productBox").prop("checked",$("#selectAll").prop("checked"))
	});
	productBoxes.click(function () {
		$("#selectAll").prop("checked",productBoxes.length===productBoxes.filter("checked").length)
	});
	
	$("#delete").click(function () {
		var selectBoxes = $(".productBox:checked");
		var selectIds=[];
		selectBoxes.each(function () {
			selectIds.push($(this).val());
		})
		console.log(selectIds)
		let flag = confirm("确认");
		if(selectIds.length > 0 && flag){
			window.location.href = "/shop/DeleteSelectServlet?selectIds=" + selectIds.join(",");
		}
	})
</script>
</HTML>

