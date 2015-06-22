<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/select2.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap-arrows.css" rel="stylesheet"/>
		
		
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/select2.js"></script>
		<script type="text/javascript" src="js/bootstrap-arrows.js"></script>
		<title>Преглед на сите пораки</title>
		<style>
			thead{
				background-color: #9acfe6;
				color:#666;
			}
		
		</style>
	</head>
	<body style="color:#777;">
		<jsp:include page="NumberOfUsers.jsp" />
			
			<div class="well well-small">
			<center><b style="align:ceter;">Табела на примени пораки</b></center>
			<table style="margin:10px;" border="1px" class="table table-striped table-bordered table-condensed" >
					<thead>
						<tr>
							<th width="20%">Испраќач</th>
							<th width="60%">Порака</th>
							<th width="20%">Примател</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${messagesReceived}">
							<tr>
								<td>${other.nameContact}</td>
								<td>${item.messageBody}</td>
								<td>${contact.nameContact}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<center><b>Табела на испратени пораки</b></center>
			<table style="margin:10px;" border="1px" class="table table-striped table-bordered table-condensed" >
					<thead>
						<tr>
							<th width="20%">Испраќач</th>
							<th width="60%">Порака</th>
							<th width="20%">Примател</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${messagesSent}">
							<tr>
								<td>${contact.nameContact}</td>
								<td>${item.messageBody}</td>
								<td>${other.nameContact}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
				<a style="margin-left:95%;" href="${pageContext.request.contextPath}/allReceivedSms"><span class="arrow-info" data-angle='270' style="transform: rotate(270deg); -webkit-transform: rotate(270deg);"></span>
				</a>
			</div>
			<jsp:include page="adminBar.jsp" />
	</body>
</html>