<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Преглед на примени пораки</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/select2.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/select2.js"></script>
		<style>
			thead{
				background-color: #9acfe6;
				color:#666;
			}
		
		</style>
	</head>
	
	<body style="color:#777;">
	
		<jsp:include page="NumberOfUsers.jsp" />
		<div class="well well-small" style="width:1000px; margin-left:10px; margin-top:10px;">
			<ul class="nav nav-tabs"style="">
				<li class="active"><a href="${pageContext.request.contextPath}/allReceivedSms">Примени</a></li>
				<li ><a href="${pageContext.request.contextPath}/allSentSms">Испратени</a></li>
				<li ><a href="${pageContext.request.contextPath}/sendNewSms">Нова порака</a></li>
			</ul>
			<div style="margin-top:10px;">
				<table border="1px" class="table table-striped table-bordered" >
					<thead style="color:#666">
						<tr>
							<th width="20%">Испраќач</th>
							<th width="80%">Порака</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${messages}">
							<tr>
								<td><a href="${pageContext.request.contextPath}/allmessages/${item.contactFrom.phone}">${item.contactFrom.nameContact}</a></td>
								<td>${item.messageBody}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<jsp:include page="adminBar.jsp" />
	</body>
</html>