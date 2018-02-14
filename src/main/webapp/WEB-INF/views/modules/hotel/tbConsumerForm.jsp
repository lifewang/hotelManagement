<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客户入住信息管理</title>
<meta name="decorator" content="default" />
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
			selectRoomList();
		});
		function selectRoomList() { //获取房间号下拉表
			$.ajax({
						url : "${ctx}/hotel/tbRoom/selectRoomList",
						type : "post",
						dataType : "json",
						success : function(data) {
							var optionstring = "";
							var count = "${fn:length(tbRoom)}";
							if (count == '0') {
								optionstring += "<option value=\"\" selected=\"selected\">--请选择--</option>";
							}
							for (var j = 0; j < data.length; j++) {
								optionstring += "<option value=\"" + data[j].id + "\">"
										+ data[j].number + "</option>";
							}

							$("#room").html(optionstring);
						}
					});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hotel/tbConsumer/">入住登记列表</a></li>
		<li class="active"><a
			href="${ctx}/hotel/tbConsumer/form?id=${tbConsumer.id}">入住登记<shiro:hasPermission
					name="hotel:tbConsumer:edit">${not empty tbConsumer.id?'修改':'填写'}</shiro:hasPermission>
				<shiro:lacksPermission name="hotel:tbConsumer:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="tbConsumer"
		action="${ctx}/hotel/tbConsumer/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="card" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">Email：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">入住时间：</label>
			<div class="controls">
				<input name="intime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${tbConsumer.intime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">离店时间：</label>
			<div class="controls">
				<input name="outtime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${tbConsumer.outtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房间号：</label>
			<div class="controls">
				<form:select path="room.id" htmlEscape="false" maxlength="64"
					class="input-xlarge required" id="room">
					<c:if test="${not empty tbConsumer}">
						<option value="${tbConsumer.room.id}" selected="selected">${tbConsumer.room.number}</option>
					</c:if>
					<c:if test="${empty tbConsumer}">
						<option value="" selected="selected">--请选择--</option>
					</c:if>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hotel:tbConsumer:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>