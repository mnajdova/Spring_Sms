<%@page import="java.util.logging.Handler"%>
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
			function send(){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/receive",
					contentType : "application/json",
					data : formToJSON(),
					dataType : 'json',
					success: (function (data){
						if(data.status==1)
							{
								$("#message").html("Пораката беше успешно пратена!");
								$("#numberFrom").val("");
								$('#numberTo').val("");
								$('#messageBody').val("");
							}
						else
							{
							$("#message").html("Пораката не беше успешно пратена!");
							}
						
					})
					
					
				});
			}
		
		
		function formToJSON() {
				  
				   return JSON.stringify({ 
				    "sender" : $("#numberFrom").val(),
				    "receiver" : $('#numberTo').val(),
				    "body" : $('#messageBody').val()
				   });
				   
				  
				  
		}
		
		
		</script>
		<title>Пратете порака</title>
	</head>
	
		<body>
		<jsp:include page="NumberOfUsersForAdmin.jsp" />
			<div class="well well-small" style="width:1000px; margin-left:10px; margin-top:10px;">
			<p id="message">&nbsp;</p>
			<div>
				<table style="width:80%">				
					<tbody>
						<tr>
							<td style="width:15%; padding-top:10px; padding-right:20px; ">
								<p style="float:right; "><b>Исраќач</b></p>
							</td>
							<td>
								<input type="text" style="width:40%; float:left;" class="form-control" name="numberFrom" id="numberFrom" ></input>
							</td>
						</tr>
						<tr>
							<td style="padding-top:10px; padding-right:20px;">
								<p style="float:right; "><b>Приматели</b></p>
							</td>
							<td>
								<input type="text" style="width:40%; float:left;" class="form-control" data-type="text" id="numberTo" name="numberTo" />
							</td>
						</tr>
						<tr>							
							<td style="vertical-align:text-top; padding-top:10px; padding-right:20px;">
								<p style="float:right; "><b>Порака</b></p>
							</td>
							<td>
								<textarea class="form-control" rows="10" name="content" id="messageBody"></textarea>
							</td>
						</tr>						
						<tr>
							<td ></td>
							<td style="padding-top:10px;"><button class="btn btn-info" style="float:right" id="send" onclick="send()">Прати</button></td>
						</tr>
					</tbody>
				</table>
			<p id="pecati"></p>
			</div>
			</div>
			<jsp:include page="adminBar.jsp" />
		</body>
</html>