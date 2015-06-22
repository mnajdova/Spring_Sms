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
				background-color: #5bc0de;
				color:#666;
			}
			tr{
				height:30px !important;
			}
		</style>
	</head>
	<body style="color:#777;">
		<jsp:include page="NumberOfUsers.jsp" />
			
		<div class="panel panel-primary" style="width:600px; margin-left:10px; border-style:solid; border-color:#ddd;">
				 <div class="panel-heading" style="background:#5bc0de; border-color:#ddd;">
                        <h3 class="panel-title">Статистики</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row-fluid" >
                            <div class="span3">
                                <img class="img-circle"
                                     src="${pageContext.request.contextPath}/img/userIcon.png"
                                     >
                         </div>
                            <div class="span6" style="float:right; margin-top:-100px; margin-right:160px;">
                                <strong>${contact.nameContact}</strong><br>
                                <table class="table table-condensed table-responsive table-user-information">
                                    <tbody>
                                    <tr>
                                        <td>Број на испратени пораки:</td>
                                        <td>${brIsprateni}</td>
                                    </tr>
                                    <tr>
                                        <td>Број на примени пораки:</td>
                                        <td>${brPrimeni}</td>
                                    </tr>
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/allSentSms">Преглед на испратените пораки <a/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/allReceivedSms">Преглед на примените пораки <a/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer" style="height:50px;">
                    	<a style="float:right; margin-top:-15px;" href="${pageContext.request.contextPath}/allReceivedSms"><span class="arrow-info" data-angle='270' style="transform: rotate(270deg); -webkit-transform: rotate(270deg);"></span>
				</a>
                       
                    </div>
				
		</div>
			
			
		<jsp:include page="adminBar.jsp" />
	</body>
</html>