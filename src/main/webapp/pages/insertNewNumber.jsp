
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/select2.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/select2.js"></script>
		<title>Најава</title>
</head>
<body style="color:#777;">
<jsp:include page="NumberOfUsersForAdmin.jsp" />
	<div class="well well-small" style="width:500px; margin-left:20px; padding-bottom:0px; padding-left:0px;padding-right:0px;">
	<p style="margin-top:0px; margin-left:10px; font-size:18px;"><b>Најава</b></p>
	<div style="background:white; padding-top:20px; padding-bottom:20px; padding-right:10px;padding-left:10px;  border-top: 1px solid #DDD; border-bottom: 1px solid #DDD;">
	<form action="${pageContext.request.contextPath}/insertPhoneNumber" method="post">
	<table>
	<tr style="margin-bottom: 10px;">
		<td><p><b>Телефонски број</b></p></td>
		<td style="padding-left:20px;"><input type="text" name="number" required></br></td>
	</tr>
	<tr>
		<td>&nbsp;</td><td></td>
	</tr>
	<tr style="margin-bottom: 10px;">
		<td></td>
		<td style="padding-left:20px;"><input type="checkbox" name="remember"> Запомни ме </td>
	
	</tr>	
	<tr style="margin-bottom: 10px;">
		<td></td>
		<td style="padding-left:20px;"><input type="submit" value="Продолжи" class="btn btn-info"></td>
	
	</tr>
	
		</table>
	</form>
	</div>
		<p style="margin-top:10px; margin-left:0px;">&nbsp; ${message}</p>
	
</div>
<h4>${error}</h4>
<jsp:include page="adminBar.jsp" />
</body>
</html>
