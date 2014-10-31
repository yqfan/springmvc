<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2>Spring MVC Application -- Sharing Photos</h2>
<ul>
<li><a href="login">Login</a> | <a href="register">Register</a></li>
<li><a href="giftchain">View Gift Chain</a></li>
<li><a href="uploadgift">Create New Gift</a></li>
<li><a href="populargift">Most Popular Gifts</a></li>
<li><a href="popularuser">Most Popular Users</a></li>
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
			<h4>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h4>
		</c:if>
 
 
	</sec:authorize>
</body>
</html>
