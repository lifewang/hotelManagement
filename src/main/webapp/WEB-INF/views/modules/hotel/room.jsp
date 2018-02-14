<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>空余客房</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hotel/tbRoom/">空余客房统计</a></li>
	</ul>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>房间号</th>
				<th>客房类型</th>
				<th>价格</th>
				<th>客房设施</th>
			</tr>
		</thead>
		<c:forEach items="${tbRoom.resultMapList}" var="resultMap">
			<tr>
				<td>${resultMap.number}</td>
				<td>${resultMap.category}</td>
				<td>${resultMap.price}</td>
				<td>${resultMap.facilitie}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>