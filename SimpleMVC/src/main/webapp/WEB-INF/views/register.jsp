<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
</head>

<body> 
<sec:authorize access="isAnonymous()">
	<!-- Login form -->
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Register Form</h2>
            
            <!-- Specifies action and HTTP method -->
            <form action="register" method="POST" role="form">
                <!-- Add CSRF token -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div id="form-group-username" class="form-group col-lg-4">
                        <label class="control-label" for="user-username"><spring:message code="label.user.username"/>:</label>
                        <!-- Add username field to the login form -->
                        <input id="user-username" name="username" type="text" class="form-control"/>
                    </div>
                </div>
 
                <div class="row">
                    <div id="form-group-password" class="form-group col-lg-4">
                        <label class="control-label" for="user-password"><spring:message code="label.user.password"/>:</label>
                        <!-- Add password field to the login form -->
                        <input id="user-password" name="password" type="password" class="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <!-- Add submit button -->
                        <button type="submit" class="btn btn-default">Register</button>
                    </div>
                </div>
            </form>
            <div class="row">
		        <div class="form-group col-lg-4">
		            <!-- Add create user account link -->
		            <a href="home">Home</a>
		        </div>
    		</div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
   	<p><spring:message code="text.register.page.authenticated.user.help"/></p>
   	<a href="home">Home</a>
</sec:authorize>

</body>
</html>