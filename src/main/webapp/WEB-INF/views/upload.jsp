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
<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Upload Example</title>
</head>
<body>
	<form:form action="upload" modelAttribute="uploadItem" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Upload Fields</legend>
			<p>
				<form:label for="name" path="name">Name</form:label>
				<br />
				<form:input path="name" />
			</p>

			<p>
				<form:label for="fileData" path="fileData">File</form:label>
				<br />
				<form:input path="fileData" type="file" />
			</p>

			<p>
				<input type="submit" />
			</p>

		</fieldset>
	</form:form>
</body>
</html>