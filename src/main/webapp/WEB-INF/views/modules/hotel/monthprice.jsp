<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>营业状况</title>
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
		<li class="active"><a href="${ctx}/hotel/tbFinance/">月收入信息</a></li>
	</ul>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>月份</th>
				<th>月总收入</th>
			</tr>
		</thead>
		<c:forEach items="${tbFinance.resultMapList}" var="resultMap">
			<tr>
				<td>${resultMap.yeardate}</td>
				<td>${resultMap.monthdate}</td>
				<td>${resultMap.income}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>