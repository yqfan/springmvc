<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>PopularUser</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/font-awesome.css"/>" />
</head>
<body>
<h2>Most Popular Users</h2>
<ul class="list-group">
  <c:forEach items="${topusers}" var="item" varStatus="loop">
    
    	<li class="list-group-item">${loop.index+1 }. ${item.getUserName()},  <i class="icon-heart" style="color:#FF1493"></i><strong> ${item.getTotalVotes() }</strong></li>
	
  </c:forEach>
</ul>
<div><a href="home">Home</a></div>
</body>
</html>