<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>营业状况</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		/* 导出财务信息 */
		$(document).ready(
				function() {
					$("#btnExport").click(
							function() {
								top.$.jBox.confirm("确认要导出财务报表吗？", "系统提示", 
										function(v, h, f) {
											if (v == "ok") {
												$("#searchForm").attr("action","${ctx}/hotel/tbFinance/export");
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
		<li class="active"><a href="${ctx}/hotel/tbFinance/">日收入信息</a></li>
		<shiro:hasPermission name="hotel:tbFinance:edit">
			<li><a href="${ctx}/hotel/tbFinance/form">日收入记录</a></li>
		</shiro:hasPermission>
	</ul>
	
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>记录日期</th>
				<th>收入</th>
				<th>备注</th>
				<shiro:hasPermission name="hotel:tbFinance:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tbFinance">
				<tr>
					<td><fmt:formatDate value="${tbFinance.updates}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${tbFinance.income}</td>
					<td>${tbFinance.remark}</td>
					<shiro:hasPermission name="hotel:tbFinance:edit">
						<td><a href="${ctx}/hotel/tbFinance/form?id=${tbFinance.id}">修改</a>
							<a href="${ctx}/hotel/tbFinance/delete?id=${tbFinance.id}"
							onclick="return confirmx('确认要删除日收入信息统计吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form:form id="searchForm" modelAttribute="tbFinance"
		action="${ctx}/hotel/tbFinance/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label style="margin-left: 850px;"></label> <input id="btnExport"
				class="btn btn-primary" type="button" value="导出" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div class="pagination">${page}</div>
</body>
</html>