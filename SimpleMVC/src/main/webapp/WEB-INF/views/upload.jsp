<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>CreateGift</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
</head>
<body>
<div class="panel panel-default">
	<div class="panel-body">
		<h2>Create and Share Your Gift</h2>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <h4>Welcome : ${pageContext.request.userPrincipal.name} </h4>  
	 
    <form method="POST" action="uploadgift?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
        <div class="row">
        	<div id="form-group-img" class="form-group col-lg-4">
				<label class="control-label" for="img">Image:</label>
				<!-- Add username field to the login form -->
				<input id="img" name="img" type="file" class="form-control"/>
            </div>
        </div>
        <div class="row">
        	<div id="form-group-title" class="form-group col-lg-4">
				<label class="control-label" for="title">Title:</label>
				<!-- Add username field to the login form -->
				<input id="title" name="title" type="text" class="form-control"/>
            </div>
        </div>
        <div class="row">
        	<div id="form-group-desc" class="form-group col-lg-4">
				<label class="control-label" for="desc">Description:</label>
				<!-- Add username field to the login form -->
				<input id="desc" name="desc" type="text" class="form-control"/>
            </div>
        </div>
        <div class="row">
			<div class="form-group col-lg-4">
				<!-- Add submit button -->
				<button type="submit" class="btn btn-default">Share Gift</button>
			</div>
		</div>
		<!-- 
        Image: <input type="file" name="file"><br /> 
        Title: <input type="text" name="title"><br />
        Description: <input type="text" name="desc"><br/> <br /> 
        <input type="submit" value="Upload"> Press here to share the gift!
         -->
    </form>
    <div><a href="home">Home</a></div>
    
    </c:if> 
	</div>
</div>
</body>
</html>