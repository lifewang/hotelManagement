<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客户入住信息管理</title>
<meta name="decorator" content="default" />

<!-- 联想搜索ajax所需包 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script type="text/javascript">
		$(document).ready(function() {
			autocompletetbConsumerName();
			autocompletetbConsumerCard();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action", "${ctx}/hotel/tbConsumer/list");
			$("#searchForm").submit();
        	return false;
        }
		/* 导出客户入住信息 */
		$(document).ready(
				function() {
					$("#btnExport").click(
							function() {
								top.$.jBox.confirm("确认要导出客户信息吗？", "系统提示", 
										function(v, h, f) {
											if (v == "ok") {
												$("#searchForm").attr("action","${ctx}/hotel/tbConsumer/export");
												$("#searchForm").submit();
											}
										}, {
												buttonsFocus : 1
										});
									stop.$('.jbox-body .jbox-icon').css('top', '55px');
							});
				});
		//联想搜索自动补全客户姓名
		function autocompletetbConsumerName() {
			$.ajax({
				url : "${ctx}/hotel/tbConsumer/selecttbConsumerName",
				type : "get",
				dataType : "json",
				success : function(data) {
					$("#name").autocomplete({
						source : data
					});
				}
			});
		}
		//联想搜索自动补全客户身份证号
		function autocompletetbConsumerCard() {
			$.ajax({
				url : "${ctx}/hotel/tbConsumer/selecttbConsumerCard",
				type : "get",
				dataType : "json",
				success : function(data) {
					$("#card").autocomplete({
						source : data
					});
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hotel/tbConsumer/">入住登记列表</a></li>
		<shiro:hasPermission name="hotel:tbConsumer:edit">
			<li><a href="${ctx}/hotel/tbConsumer/form">入住登记填写</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbConsumer"
		action="${ctx}/hotel/tbConsumer/" method="post"
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
			<li><label></label> <input id="btnExport"
				class="btn btn-primary" type="button" value="导出" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>手机号</th>
				<th>身份证号</th>
				<!-- <th>Email</th> -->
				<th>入住时间</th>
				<th>离店时间</th>
				<th>房间号</th>
				<th>备注</th>
				<shiro:hasPermission name="hotel:tbConsumer:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tbConsumer">
				<tr>
					<td><a href="${ctx}/hotel/tbConsumer/form?id=${tbConsumer.id}">
							${tbConsumer.name} </a></td>
					<td>${tbConsumer.phone}</td>
					<td>${tbConsumer.card}</td>
					<%-- <td>${tbConsumer.email}</td> --%>
					<td><fmt:formatDate value="${tbConsumer.intime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${tbConsumer.outtime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a
						href="${ctx}/hotel/tbRoom/listrooms?number=${tbConsumer.room.number}">
							${tbConsumer.room.number}</a></td>
					<td>${tbConsumer.remark}</td>
					<shiro:hasPermission name="hotel:tbConsumer:edit">
						<td><a
							href="${ctx}/hotel/tbConsumer/form?id=${tbConsumer.id}">修改</a> <a
							href="${ctx}/hotel/tbConsumer/delete?id=${tbConsumer.id}"
							onclick="return confirmx('确认要删除入住信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>