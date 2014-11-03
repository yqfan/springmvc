<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/font-awesome.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/social-buttons-3.css"/>" />
</head>

<body> 
<sec:authorize access="isAnonymous()">
	<!-- Login form -->
    <div class="panel panel-default">
        <div class="panel-body">
            <h2><spring:message code="label.login.form.title"/></h2>
            
            <!-- Error message is shown if login fails. -->
            <c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			
            <!-- Specifies action and HTTP method -->
            <form action="<c:url value='/j_spring_security_check' />" method="POST" role="form">
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
                        <button type="submit" class="btn btn-default"><spring:message code="label.user.login.submit.button"/></button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="form-group col-lg-4">
                    <!-- Add create user account link -->
                    <a href="register"><spring:message code="label.navigation.registration.link"/></a>
                </div>
            </div>
        </div>
    </div>
	<!-- Social Sign In Buttons -->
    <div class="panel panel-default">
        <div class="panel-body">
            <h2><spring:message code="label.social.sign.in.title"/></h2>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <!-- Add Facebook sign in button -->
                    <a href="${pageContext.request.contextPath}/auth/facebook"><button class="btn btn-facebook"><i class="icon-facebook"></i> | <spring:message code="label.facebook.sign.in.button"/></button></a>
                </div>
            </div>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <!-- Add Twitter sign in Button -->
                    <a href="${pageContext.request.contextPath}/auth/twitter"><button class="btn btn-twitter"><i class="icon-twitter"></i> | <spring:message code="label.twitter.sign.in.button"/></button></a>
                </div>
            </div>
        </div>
    </div>
	<div class="row">
        <div class="form-group col-lg-4">
            <!-- Add create user account link -->
            <a href="home">Home</a>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
   	<p><spring:message code="text.login.page.authenticated.user.help"/></p>
   	<a href="home">Home</a>
</sec:authorize>
</body>
</html>