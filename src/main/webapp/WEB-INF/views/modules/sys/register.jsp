<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>用户注册页面</title>

<meta name="description" content="用户注册">
<meta name="decorator" content="blank" />
<link rel="stylesheet" href="${ctxStatic}/register/css/style.css" />
<link rel="stylesheet" href="${ctxStatic}/register/css/easyform.css" />

<script type="text/javascript"
	src="${ctxStatic}/register/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/register/js/easyform1.js"></script>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("searchForm").attr("action", "${ctx}/sys/user/registerUser");
		$("searchForm").submit();
		return false;
	}
	function yan(){

		   if(form.name.value==""){

		    	alert("昵称不能为空！");//弹出提示框
		    	document.form.name.focus();  //获取鼠标焦点
		   	 	return(false);//返回一个值

		  }if(form.name.value.length<2 || form.name.value.length>15){
			  
			    alert("昵称长度限制在2-15位");//弹出提示框
		    	document.form.name.focus();  //获取鼠标焦点
		   	 	return(false);//返回一个值
		  }
		  if(form.loginName.value==""){

		    	alert("登录名不能为空！");//弹出提示框
		    	document.form.loginName.focus();  //获取鼠标焦点
		   	 	return(false);//返回一个值

		  }if(form.loginName.value.length != 11){
			  
			    alert("登录名长度必须为11位");//弹出提示框
		    	document.form.loginName.focus();  //获取鼠标焦点
		   	 	return(false);//返回一个值
		  }
		  if(form.newPassword.value==""){

			    alert("请输入密码！");//弹出提示框
			    document.form.newPassword.focus();  //获取鼠标焦点
			    return(false);//返回一个值

		 }if(form.newPassword.value.length<6 || form.newPassword.value.length>20){
		 	  
		    alert("昵称长度限制在6-20位");//弹出提示框
	    	document.form.newPassword.focus();  //获取鼠标焦点
	   	 	return(false);//返回一个值
	     }if(form.newPassword.value != form.confirmnewPassword.value){

		    alert("两次输入的密码不同！");//弹出提示框
		    document.form.newPassword.focus();  //获取鼠标焦点
		    return(false);//返回一个值

		  }
	}
	
</script>
</head>

<body bgcolor="red">

	<div class="form-div">
		
		<form id="inputForm" action="${ctx}/sys/user/registerUser"
			method="post" onsubmit = "return yan()" name = "form">

			<sys:message content="${message }" />
			<table>
				<tbody>
					<tr>
						<td>昵称</td>
						<td>
							<input name="name" id="name" type="text"/>
						</td>
					</tr>
					<tr>
						<td>登录名</td>
						<td>
							<input name="loginName" id="loginName" type="text"/>
						</td>
					</tr>
					<tr>
						<td>密码</td>
						<td>
							<input name="newPassword" id="newPassword" type="password"/>
						</td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td>
							<input name="confirmnewPassword" id="confirmnewPassword" type="password" />
						</td>
					</tr>
				</tbody>
			</table>
			<div class="buttons" style="margin-top: 5px;">
				<input style="margin-top: 5px; margin-left: 10px;" type="submit"
					value="注 册"> <a href="${ctx}/login"><input
					style="margin-top: 5px; margin-left: 30px;" type="button"
					value="返回登录"></a>
			</div>

			<br class="clear">
		</form>
	</div>

</body>

</html>