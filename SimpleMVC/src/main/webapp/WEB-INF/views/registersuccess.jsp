<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
</head>
<body>
	<h2>Spring MVC Register</h2>
 
	
	<strong> ${name} </strong>: - ${message}.
	
    <a class="textLink" href="<c:url value="${redirect}" />" ><c:out value="${redirect}" /></a>
 
</body>
</html>