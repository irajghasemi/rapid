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
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/calendar.css"  media="screen"type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/layout.css" media="screen"type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/calendar.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/dateBubble.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css//menu.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/socialButtons.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/superButton.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/chinaButton.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/formglass.css" media="screen" type="text/css" />
<title>Insert title here</title>
</head>
<body>
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
  <li><a href="http://localhost:8080/rapid/login">Log In</a></li>
</ul>
     
     </header>
 <div id='main' >

    <article >
<div align="center">
			<form action="signup" method="POST" modelAttribute="user" autocomplete="off">
			    <ul class="errorMessages"></ul>
			<h1>Sign Up</h1>
			<input type="text" name="firstname" placeholder="firstname" required>
			<input type="text" name="surname" placeholder="lastname" required>
			<input type="text" name="username" placeholder="username" required onfocus="value=''">
			<input type="password" name="password" placeholder="password" required onfocus="value=''">
			<input type="email" name="email" placeholder="email" required="required" >
			<input type="text" name="ssn" placeholder="person number" required>
<!-- 			<input type="submit" class="btn" value="Submit"> -->
    <div class="buttons">
        <button type="submit" value="Submit" class="btn">Sign Up</button>
    </div>

			<br><br>
		</form>
		<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
  			<defs>
    			<filter id="blur" x="0" y="0">
      				<feGaussianBlur in="SourceGraphic" stdDeviation="5" />
    			</filter>
  			</defs>
		</svg>
			
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
 <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
  <script src="<%=request.getContextPath()%>/resources/login-form/js/index.js"></script>
   <script src="<%=request.getContextPath()%>/resources/login-form/js/prefixfree.min.js"></script>
   <script src="<%=request.getContextPath()%>/resources/js/formError.js"></script>
   <script src="js/chinaButton.js"></script>
 <footer>
	 
 </footer>
  
  </body>
</html>
