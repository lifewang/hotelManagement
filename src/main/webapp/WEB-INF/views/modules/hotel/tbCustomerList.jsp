<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>入住信息管理</title>
<meta name="decorator" content="default" />

<!-- 联想搜索ajax所需包 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script type="text/javascript">
		$(document).ready(function() {
			autocompleteCustomerName();
			autocompleteCustomerCard();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action", "${ctx}/hotel/tbCustomer/list");
			$("#searchForm").submit();
        	return false;
        }
		
		//联想搜索自动补全客户姓名
		function autocompleteCustomerName(){
			$.ajax({
				url:"${ctx}/hotel/tbCustomer/selectCustomerName",
				type:"get",
				dataType:"json",
				success:function(data){
					$("#name").autocomplete({
						source:data
					});
				}
			});
		}
		//联想搜索自动补全客户证件号
		function autocompleteCustomerCard(){
			$.ajax({
				url:"${ctx}/hotel/tbCustomer/selectCustomerCard",
				type:"get",
				dataType:"json",
				success:function(data){
					$("#card").autocomplete({
						source:data
					});
				}
			});
		}
		/* 导入导出客户入住信息 */
		$(document).ready(
				function() {
					$("#btnExport").click(
							function() {
								top.$.jBox.confirm("确认要导出客户入住信息吗？", "系统提示", 
										function(v, h, f) {
											if (v == "ok") {
												$("#searchForm").attr("action","${ctx}/hotel/tbCustomer/export");
												$("#searchForm").submit();
											}
										}, {
												buttonsFocus : 1
										});
									stop.$('.jbox-body .jbox-icon').css('top', '55px');
							});
				});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hotel/tbCustomer/">预订信息列表</a></li>
		<shiro:hasPermission name="hotel:tbCustomer:edit">
			<li><a href="${ctx}/hotel/tbCustomer/form">预订信息填写</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbCustomer"
		action="${ctx}/hotel/tbCustomer/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>姓名：</label> <form:input path="name"
					htmlEscape="false" maxlength="50" class="input-medium" id="name" />
			</li>
			<li><label>身份证号：</label> <form:input path="card"
					htmlEscape="false" maxlength="50" class="input-medium" id="card" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" onclick="return page();" /></li>
			<!-- <li><label></label>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出" /> 
			</li> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>入住人</th>
				<th>性别</th>
				<!-- <th>出生年月</th> -->
				<th>手机号</th>
				<th>身份证号</th>

				<!-- <th>证件类型</th> 
				<th>国籍</th> 
				<th>Email</th> 
				<th>公司</th> 
				<th>地址</th>
				<th>房间偏好</th> -->
				<th>房间号</th>
				<th>住店时间</th>
				<th>离店时间</th>
				<th>是否预定</th>
				<th>备注</th>
				<shiro:hasPermission name="hotel:tbCustomer:edit">
					<th>操作</th>
				</shiro:hasPermission>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tbCustomer">
				<tr>
					<td><a href="${ctx}/hotel/tbCustomer/form?id=${tbCustomer.id}">
							${tbCustomer.name} </a></td>
					<td>${fns:getDictLabel(tbCustomer.sex, 'sex', '')}</td>
					<%-- <td>
					<fmt:formatDate value="${tbCustomer.datebirth}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
					<td>${tbCustomer.phone}</td>
					<td>${tbCustomer.card}</td>
					<%-- 
				<td>
					${fns:getDictLabel(tbCustomer.cardtype, 'card_type', '')}
				</td>
				<td>
					${tbCustomer.country}
				</td> 
					<td>${tbCustomer.email}</td>
				<td>
					${tbCustomer.company}
				</td>
					<td>${tbCustomer.address}</td>
					<td>${fns:getDictLabel(tbCustomer.love, 'love', '')}</td> --%>
					<td><a
						href="${ctx}/hotel/tbRoom/listrooms?number=${tbCustomer.room.number}">
							${tbCustomer.room.number}</a></td>
					<td><fmt:formatDate value="${tbCustomer.intime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${tbCustomer.outtime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a href="${ctx}/hotel/tbCustomer/checkout?id=${tbCustomer.id}">
						<c:if test="${tbCustomer.state eq '已预订' }">已预订</c:if> 
						<c:if test="${tbCustomer.state eq '未预订' }">未预订</c:if>
					</a></td>
					<td>${tbCustomer.remark}</td>
					<shiro:hasPermission name="hotel:tbCustomer:edit">
						<td><a
							href="${ctx}/hotel/tbCustomer/form?id=${tbCustomer.id}">修改</a> <a
							href="${ctx}/hotel/tbCustomer/delete?id=${tbCustomer.id}"
							onclick="return confirmx('确认要删除该预定信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>