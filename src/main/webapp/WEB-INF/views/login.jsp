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
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/calendar.css"  media="screen"type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/layout.css" media="screen"type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/calendar.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/dateBubble.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css//menu.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/socialButtons.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/formglass.css" media="screen" type="text/css" />
<title>Insert title here</title>
<script type="text/javascript">


</script>
</head>
<body onload='document.loginForm.username.focus();' >
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
  <li><a href="http://localhost:8080/rapid/lostPassword">Forgot Password</a></li>
</ul>
     
     </header>
 <div id='main' >

    <article >
    <div style="clear:left; height:50px;"></div>
<div align="center" >
<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
			<form name='loginForm' action="<c:url value='/login' />" method='POST'  autocomplete="off">
			<h1>Log In</h1>
			<input type="text" name="username" placeholder="username">
			<input type="password" name="password" placeholder="password">
			<input type="submit" class="btn" value="Submit">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<br><br>
		</form>

			
</div>

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
<%--   <script src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.11.2.custom/jquery-ui.min.js"></script> --%>
<%--    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script> --%>
<%--    <script src="<%=request.getContextPath()%>/resources/js/yui-min.js"></script> --%>
   <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
 <footer>
	 
 </footer>
  
  </body>
</html>
