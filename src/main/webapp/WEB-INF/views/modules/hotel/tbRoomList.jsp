<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客房信息管理</title>
<meta name="decorator" content="default" />

<!-- 联想搜索ajax所需包 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script type="text/javascript">
		$(document).ready(function() {
			autocompleteRoomNumber();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//联想搜索自动补房间号
		function autocompleteRoomNumber(){
			$.ajax({
				url : "${ctx}/hotel/tbRoom/selectRoomNumber", 
				type : "get",    
				dataType : "json",  
				success : function(data) {    
					 $( "#number" ).autocomplete({
					      source: data
					    }); 
				}
			});
		}
		/* 重置查询函数 */
		function clearCondition(){
			$(':input','#searchForm').
			not(':button,:submit,:reset,:hidden').
			val('').
			removeAttr('checked').
			removeAttr('selected');
			$("#searchForm").submmit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hotel/tbRoom/">客房信息列表</a></li>
		<shiro:hasPermission name="hotel:tbRoom:edit">
			<li><a href="${ctx}/hotel/tbRoom/form">客房信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbRoom"
		action="${ctx}/hotel/tbRoom/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>客房状态：</label> <form:select path="status"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('y_n')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>房间号：</label> <form:input path="number"
					htmlEscape="false" maxlength="20" class="input-medium" id="number" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li><label></label> <input id="btnSubmit"
				class="btn btn-primary" type="submit" value="重置查询"
				onclick="clearCondition();"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>房间号</th>
				<th>客房类型</th>
				<th>客房状态</th>
				<th>价格</th>
				<th>客房设施</th>
				<th>预定状态</th>
				<th>备注</th>
				<shiro:hasPermission name="hotel:tbRoom:edit">
					<th>操作</th>
				</shiro:hasPermission>
				<th>是否结账</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tbRoom">
				<tr>
					<td><a href="${ctx}/hotel/tbRoom/form?id=${tbRoom.id}">
							${tbRoom.number} </a></td>
					<td>${fns:getDictLabel(tbRoom.category, 'category_type', '')}
					</td>
					<td>${fns:getDictLabel(tbRoom.status, 'y_n', '')}</td>
					<td>${tbRoom.price}</td>
					<td>${tbRoom.facilitie}</td>
					<td>${tbRoom.customer.state}</td>
					<td>${fns:getDictLabel(tbRoom.remark, 'remark', '')}</td>
					<shiro:hasPermission name="hotel:tbRoom:edit">
						<td><a href="${ctx}/hotel/tbRoom/form?id=${tbRoom.id}">修改</a>
							<a href="${ctx}/hotel/tbRoom/delete?id=${tbRoom.id}"
							onclick="return confirmx('确认要删除该客房信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
					<td><a href="${ctx}/hotel/tbRoom/checkout?id=${tbRoom.id}">
							<c:if test="${tbRoom.state eq '是' }">已结账</c:if> <c:if
								test="${tbRoom.state eq '否' }">未结账</c:if>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>