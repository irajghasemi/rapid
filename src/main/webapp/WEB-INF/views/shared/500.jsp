<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->

<head>
   <meta charset="utf-8">
	<meta name="viewport" content="width=device-width" />   
<link href="<%=request.getContextPath()%>/resources/css/html5reset-1.6.1.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/error.css" rel="stylesheet" type="text/css" />
 <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'>
 <!-- IE Fix for HTML5 Tags -->
	<!--[if lt IE 9]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>  
    <script src="<%=request.getContextPath()%>/resources/js/jquery.novacancy.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.novacancy.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#error').novacancy({
                'reblinkProbability': 0.1,
                'blinkMin': 0.2,
                'blinkMax': 0.6,
                'loopMin': 8,
                'loopMax': 10,
                'color': '#ffffff',
                'glow': ['0 0 80px #ffffff', '0 0 30px #008000', '0 0 6px #0000ff']
            }); 

            $('#error_nubmer').novacancy({
                'blink': 1,
                'off': 1,
                'color': 'Red',
                'glow': ['0 0 80px Red', '0 0 30px FireBrick', '0 0 6px DarkRed']
            });
        });
    </script>
    </head>
<body>
	

 <div class="board">
        <p id="error">SERVER ERROR</p>
        <p id="error_nubmer">500</p>
    </div>
		
</body>
</html>