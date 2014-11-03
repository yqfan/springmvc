<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>GiftChain</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
</head>
<body>
<h2>All Gifts in Gift Chain</h2>
<ul class="list-group">
  <c:forEach items="${gifts}" var="item">
    
    	<li class="list-group-item"><a class="textLink" style="color: green" href="<c:url value="/giftdetail?id=${item.id }" />" ><c:out value="${item.title}" /></a></li>
	
  </c:forEach>
</ul>
<div><a href="home">Home</a></div>
</body>
</html>
