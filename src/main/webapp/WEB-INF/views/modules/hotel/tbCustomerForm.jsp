<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>入住信息管理</title>
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
		<li><a href="${ctx}/hotel/tbCustomer/">预订信息列表</a></li>
		<li class="active"><a
			href="${ctx}/hotel/tbCustomer/form?id=${tbCustomer.id}">预订信息<shiro:hasPermission
					name="hotel:tbCustomer:edit">${not empty tbCustomer.id?'修改':'填写'}</shiro:hasPermission>
				<shiro:lacksPermission name="hotel:tbCustomer:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="tbCustomer"
		action="${ctx}/hotel/tbCustomer/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">入住人：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:select path="sex" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('sex')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="card" htmlEscape="false" maxlength="50"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%-- 
		<div class="control-group">
			<label class="control-label">国籍：</label>
			<div class="controls">
				<form:input path="country" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Email：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房间偏好：</label>
			<div class="controls">
				<form:select path="love" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('love')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div> --%>

		<div class="control-group">
			<label class="control-label">房间号：</label>
			<div class="controls">
				<form:select path="room.id" htmlEscape="false" maxlength="64"
					class="input-xlarge required" id="room">
					<c:if test="${not empty tbCustomer}">
						<option value="${tbCustomer.room.id}" selected="selected">${tbCustomer.room.number}</option>
					</c:if>
					<c:if test="${empty tbCustomer}">
						<option value="" selected="selected">--请选择--</option>
					</c:if>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">住店时间：</label>
			<div class="controls">
				<input name="intime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${tbCustomer.intime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">离店时间：</label>
			<div class="controls">
				<input name="outtime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${tbCustomer.outtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否预定：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="4"
					maxlength="100" class="input-xxlarge " />
			</div>
		</div>
		<!-- 区域 -->
		<form:hidden path="user.id" value="${fns:getUser().id}" />
		<!-- 地区 -->
		<form:hidden path="area.code" value="${fns:getUser().areaId} " />

		<div class="form-actions">
			<shiro:hasPermission name="hotel:tbCustomer:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="提 交" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="取消"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>