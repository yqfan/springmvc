<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Most Popular Users View</h2>
<ul>
  <c:forEach items="${topusers}" var="item">
    
    	<li>${item.getUserName()},<strong>Touch: ${item.getTotalVotes() }</strong></li>
	
  </c:forEach>
</ul>
<div><a href="home">Home</a></div>
</body>
</html>