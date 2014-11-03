<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>${gift.title }</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/font-awesome.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/social-buttons-3.css"/>" />
</head>
<body>
	<h2>${gift.title } by ${gift.owner }</h2>
	
	<img src="imagedisplay?id=${gift.id}" alt="image is not available" />
	<div class="container">
		<div class="row">
			Description : "<strong> ${gift.description } </strong>"
		</div>
		<div class="row">
			<sec:authorize access="hasRole('ROLE_USER')">
			 	<form name="voteForm" action="giftdetail?id=${gift.id }" method="POST" > 
			 		<button type="submit" name="vote" style="color:#FF1493;background-color:transparent;border:none;padding:0"><i class="icon-heart"></i></button> <strong> ${gift.touchCount }</strong>
			 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</sec:authorize>
		</div>
		
	</div>
	
 	
	
 	<div><a href="giftchain">View Gift Chain</a></div>
 	<div><a href="home">Home</a></div>
</body>
</html>