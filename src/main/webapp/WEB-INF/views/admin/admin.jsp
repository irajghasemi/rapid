<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,java.io.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/calendar.css"  media="screen"type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/layout.css" media="screen"type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/calendar.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/dateBubble.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css//menu.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/socialButtons.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/table/style.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap-theme.css" media="screen" type="text/css" />
<title>Admin Page</title>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>

</head>
<body class="yui3-skin-sam  yui-skin-sam">
 <header>     
    <ul id="menu">
  <li><a href="http://localhost:8080/rapid/">Home</a></li>
  <li><a href="#">Categoried</a>
    <ul>
      <li><a href="http://www.codepen.io">CodePen</a></li>
      <li><a href="http://www.zas.se/viwes/textEditor">Text Editor</a></li>
      <li><a href="#">Development tools</a></li>
      <li><a href="#">Web design</a></li>
    </ul>
  </li>
  <li><a href="#">Work</a>
    <ul>
      <li><a href="#">A girl</a></li>
      <li><a href="#">Two pigs</a></li>
      <li><a href="#">Three birds</a></li>
      <li><a href="#">Four boys</a></li>
    </ul>
  </li>
  <li><a href="#">About</a></li>
  <li><a href="#">Content</a></li>
  <li><a href="http://localhost:8080/rapid/signup">Sign Up</a></li>
 <li><a href="javascript:formSubmit()">Log out</a></li>
   <li ><a style="color: #64e0ef; width: 15px; text-align:left; left: auto;" >${pageContext.request.userPrincipal.name}</a></li>
</ul>
     
     </header>
 <div id='main' >

    <article >
	     <div style="clear:left; height:30px;"></div>
	  <div class="caption">All Users</div>
	<div id="table">
		<div class="header-row row">
	    <span class="cell primary">Name</span>
	    <span class="cell">Surname</span>
	     <span class="cell">Email</span>
	    <span class="cell">SSN</span>
	     <span class="cell">Edit</span>
	     <span class="cell">Delete</span>
	  </div>
	  <c:forEach items="${users }" var="user">
	  <div class="row">
		<input type="radio" name="expand">
	    <span class="cell primary" data-label="firstname">${user.firstname}</span>
	    <span class="cell" data-label="surname">${user.surname}</span>
	     <span class="cell" data-label="email">${user.email}</span>
	     <span class="cell" data-label="ssn">${user.ssn}</span>
	     <span class="cell" data-label="edit"><a href="editUser?ssn=${user.ssn}">Edit</a></span>
<%-- 	 <span class="cell" data-label="delete"><a type="submit" href="deleteUser?ssn=${user.ssn}" onclick="return confirm('Are you sure? Do you want to delete the User?')">Delete</a></span> --%>	  
	 <button class='btn btn-danger btn-xs'  id="btnDelete" type="submit" name="remove_levels" value="delete"
	 onclick="if (confirm('Are you sure you want to delete: ${user.firstname} ${user.surname}?')) { window.location='deleteUser?ssn=${user.ssn}';} else { return false; }"><span class="fa fa-times"></span>Delete</button>

	 </div>
	 </c:forEach>
	 <!-- modal content -->
	</article>

    <nav>
    <div id="social-links">
      <ul id="social-icons">
        <li class="facebook"><a class="zocial-facebook" href="#"></a></li>
        <li class="twitter"><a class="zocial-twitter" href="#"></a></li>
        <li class="linkedin"><a class="zocial-linkedin" href="#"></a></li>
        <li class="forrst"><a class="zocial-forrst" href="#"></a></li>
        <li class="github"><a class="brandico-github"href="#"></a></li>
        <li class="instagram"><a class="brandico-instagram" href="#"></a></li>
      </ul>
    </div>
    </nav>
<aside>
    
    <div class="calendar-icon">
  <div class="calendar-weekday">
    Wed
  </div>
  <div class="calendar-icon-body">
    <div class="calendar-monthday">
      24
    </div>
    <div class="calendar-month">
      Aug
    </div>
<div style="clear:left; height:6px;"></div>
<div class="calendar-year"></div>
  </div>
</div>
</aside> </div>
 <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
  <script src="<%=request.getContextPath()%>/resources/login-form/js/index.js"></script>
   <script src="<%=request.getContextPath()%>/resources/login-form/js/prefixfree.min.js"></script>
   <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
   <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.js"></script>
   <script src="<%=request.getContextPath()%>/resources/bootstrap/js/npm.js"></script>
   <script src="<%=request.getContextPath()%>/resources/bootstrap/js/confirm.js"></script>
   
<script src="http://code.jquery.com/jquery-2.0.3.min.js" data-semver="2.0.3" data-require="jquery"></script>
 <footer>
 <div >
	 <c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
 </div>
 </footer>
  
  </body>
</html>
