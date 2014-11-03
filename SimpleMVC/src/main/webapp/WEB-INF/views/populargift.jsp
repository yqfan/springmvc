<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>PopularGift</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/font-awesome.css"/>" />
</head>
<body>
<h2>Most Popular Gifts</h2>
<ul class="list-group">
  <c:forEach items="${topgifts}" var="item" varStatus="loop">
    
    	<li class="list-group-item">${loop.index+1 }. <a class="textLink" style="color: green" href="<c:url value="/giftdetail?id=${item.id }" />" ><c:out value="${item.title}" /></a>, Owner: ${item.owner },  <i class="icon-heart" style="color:#FF1493"></i><strong> ${item.touchCount }</strong></li>
	
  </c:forEach>
</ul>
<div><a href="home">Home</a></div>
</body>
</html>