
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/select2.js"></script>
		
		
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/select2-bootstrap.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/select2.css" rel="stylesheet"/>
		
		
		
		<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery('#numberTo').select2({tags:[${contacts}], placeholder: "Insert a number" });
				
		})	
		</script>
		<title>Пратете порака</title>
	</head>
	
		<body style="color:#777;">
		
		<jsp:include page="NumberOfUsers.jsp" />
			<div class="well well-small" style="width:1000px; margin-left:10px; margin-top:10px;">
			<ul class="nav nav-tabs"style="">
			<li ><a href="${pageContext.request.contextPath}/allReceivedSms">Примени</a></li>
			<li ><a href="${pageContext.request.contextPath}/allSentSms">Испратени</a></li>
			<li class="active"><a href="#">Нова порака</a></li>
			</ul>
			<div style="margin-top:10px;">
			<p style="height:12px;">${message}</p>
			<form method="POST"  action="${pageContext.request.contextPath}/sendNewSms">
				<table style="width:80%">				
					<tbody>
						<tr>
							<td style="width:15%; padding-top:10px; padding-right:20px; "><p style="float:right; "><b>Исраќач</b></p></td>
							<td><input type="text" style="width:40%; float:left;" class="form-control" id="disabledInput" name="numberFrom" readonly="true" value="${contact.phone}"></input></td>
						</tr>
						<tr>
							<td style="padding-top:10px; padding-right:20px;"><p style="float:right; "><b>Приматели</b></p></td>
							<td><input type="text" style="width:40%; float:left;" class="form-control" data-type="text" id="numberTo" name="numberTo"/></td>
						</tr>
						<tr>							
							<td style="vertical-align:text-top; padding-top:10px; padding-right:20px;"><p style="float:right; "><b>Порака</b></p></td>
							<td><textarea class="form-control" rows="10" name="content"></textarea></td>
						</tr>						
						<tr>
							<td ></td>
							<td style="padding-top:10px;"><input class="btn btn-info" style="float:right" type="submit" value="Прати" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			
			</div>
			</div>
			<jsp:include page="adminBar.jsp" />
		</body>
</html>