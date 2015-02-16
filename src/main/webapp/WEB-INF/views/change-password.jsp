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
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/dateBubble.css" media="screen" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css//menu.css" media="screen" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/flex/css/socialButtons.css" media="screen" type="text/css" />
<title>Insert title here</title>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>
	
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
  <li><a href="javascript:formSubmit()">Log out</a></li>
  <li ><a style="color: #64e0ef;">User: ${pageContext.request.userPrincipal.name}</a></li>
</ul>
     
     </header>
     
 <div id='main' >

    <article >
     <div style="clear:left; height:30px;"></div>
  <div>
  <form:form modelAttribute="form" cssClass="wufoo topLabel clearfix">
	        <form:errors path="*" cssClass="errorBox" element="div" />
	        <ul>
	            <li>
	                <form:label path="original" cssClass="desc">Enter your original password <span class="req">*</span></form:label>
	                <div>
	                    <form:password path="original" id="original" cssClass="field text large" maxlength="32" tabindex="1" />
	                    <form:label path="original">Note: Your password is case sensative.</form:label>
						<div style="height:14px"></div>
	                </div>
	            </li>
	            <li class="leftHalf">
	                <form:label path="password" cssClass="desc">Choose a new password <span class="req">*</span></form:label>
	                <div>
	                    <form:password path="password" id="password" cssClass="field text medium" maxlength="255" tabindex="2" />
	                    <form:label path="password">Must be at least 6 characters long.</form:label>
						<div style="height:14px"></div>
	                </div>
	            </li>
	            <li class="rightHalf">
						<div style="height:4px"></div>
	                <form:label path="confirm" cssClass="desc">Verify your new password <span class="req">*</span></form:label>
	                <div>
	                    <form:password path="confirm" id="confirm" cssClass="field text medium" maxlength="255" tabindex="3" />
	                    <form:label path="confirm"></form:label>
	                </div>
	            </li>
	            <li class="buttons">
	            <li><a href="<c:url value="/j_spring_security_logout" />">
<!-- 	                <input id="saveForm" class="btTxt" type="submit" tabindex="4" value="Change Password"/> -->
	                <button class="button blue medium " type="submit" >Change Password</button>
				</a></li>
	            <li class="cancel">
				<div style="height:50px"></div>
	                <a href="<c:url value='/'/>"> 
	                <br/>
	                <br/>
	                <br/>
	                Or, return to the home page.</a>
	            </li>
	        </ul>
	    </form:form>  
  
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
   <script src="js/chinaButton.js"></script>
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
