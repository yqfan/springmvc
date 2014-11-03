<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
</head>
<body>

	<h2>Spring MVC Application -- Sharing Gifts</h2>
	<ul class="list-group">
	<li class="list-group-item"><a href="login">Login</a> | <a href="register">Register</a></li>
	<li class="list-group-item"><a href="giftchain">View Gift Chain</a></li>
	<li class="list-group-item"><a href="uploadgift">Create New Gift</a></li>
	<li class="list-group-item"><a href="populargift">Most Popular Gifts</a></li>
	<li class="list-group-item"><a href="popularuser">Most Popular Users</a></li>
	</ul>
	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
 
		<c:if test="${pageContext.request.userPrincipal.name != null}">
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
		</c:if>
 
 
	</sec:authorize>
</body>
</html>
