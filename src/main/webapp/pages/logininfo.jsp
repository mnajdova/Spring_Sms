<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<nav class="navbar navbar-default" role="navigation">
<ul class="nav navbar-nav navbar-right">
    <li>
      	<div style="float: right; color:#777; width:220px;" class="nav navbar-nav">
		<c:if test="${pageContext.request.userPrincipal.authenticated}">
			<div style="float:left; padding-top:10px;">Најавени сте како <b>${pageContext.request.userPrincipal.principal.username}</b> </div>
			<div style="float:right;"><a style=" margin:5px;" href="${pageContext.request.contextPath}/j_spring_security_logout"><img src="${pageContext.request.contextPath}/img/Logout.png"></img></a></div>
		</c:if>
		</div>
	</li>
   </ul>
</nav>