<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>

<h2>Create and Share Your Gift</h2>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <h4>Welcome : ${pageContext.request.userPrincipal.name} </h4>  
	 
    <form method="POST" action="uploadgift?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
        Image: <input type="file" name="file"><br /> 
        Title: <input type="text" name="title"><br />
        Description: <input type="text" name="desc"><br/> <br /> 
        <input type="submit" value="Upload"> Press here to upload the gift!
        
    </form>
    <div><a href="home">Home</a></div>
    
    </c:if> 
</body>
</html>