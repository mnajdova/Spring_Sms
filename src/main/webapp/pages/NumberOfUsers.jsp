<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">SMS Manager</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li><a href="#">Моментално има ${numUsers} активни корисници<br />
		</a></li>
      <li><a href="#"></a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">Салдо <b>${saldo}</b></a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>${contact.nameContact} </b><b class="caret"></b></a>
        <ul class="dropdown-menu">
        	<li><a href="${pageContext.request.contextPath}/statistic">Статистики</a></li>
        	<li class="divider"></li>
          <li><a href="${pageContext.request.contextPath}/insertNewNumber"><img src="${pageContext.request.contextPath}/img/System-Logout-icon.png"></img>&nbsp;&nbsp;Одјава</a></li>
        </ul>
      </li>
    </ul>
  </div><!-- /.navbar-collapse -->
</nav>
