<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Gift Chain View</h2>
<ul>
  <c:forEach items="${gifts}" var="item">
    
    	<li><a class="textLink" style="color: green" href="<c:url value="/giftdetail?id=${item.id }" />" ><c:out value="${item.title}" /></a></li>
	
  </c:forEach>
</ul>
<div><a href="home">Home</a></div>
</body>
</html>
