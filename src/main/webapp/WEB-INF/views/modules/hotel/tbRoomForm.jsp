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
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			autocompleteRoomNumber();
			autocompleteRoomCategory();
			autocompleteRoomPrice();
		});

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
		
		//联想搜索获取房间类型
		function autocompleteRoomCategory(){
			$.ajax({
				url : "${ctx}/hotel/tbRoom/selectRoomCategory",
				type : "get",
				dataType : "json",
				success : function(data) {
					 $( "#category" ).autocomplete({
					      source: data
					    }); 
				}
			});
		}
		//联想搜索获取房间价格
		function autocompleteRoomPrice(){
			$.ajax({
				url : "${ctx}/hotel/tbRoom/selectRoomPrice",
				type : "get",
				dataType : "json",
				success : function(data) {
					 $( "#price" ).autocomplete({
					      source: data
					    }); 
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hotel/tbRoom/">客房信息列表</a></li>
		<li class="active"><a
			href="${ctx}/hotel/tbRoom/form?id=${tbRoom.id}">客房信息<shiro:hasPermission
					name="hotel:tbRoom:edit">${not empty tbRoom.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="hotel:tbRoom:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="tbRoom"
		action="${ctx}/hotel/tbRoom/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">房间号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="20"
					class="input-xlarge required" id="number" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客房类型：</label>
			<div class="controls">
				<form:select path="category" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('category_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客房状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('y_n')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false"
					class="input-xlarge required" id="price" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客房设施：</label>
			<div class="controls">
				<form:input path="facilitie" htmlEscape="false"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:select path="remark" class="input-xlarge">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('remark')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否结账：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('Y_N')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hotel:tbRoom:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>

</html>