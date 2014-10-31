<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
	<h2>Spring MVC Gift Detail View</h2>
 
	Title : "<strong> ${gift.title} </strong>" <br>
	Description : "<strong> ${gift.description } </strong>" <br>
	Owner : "<strong> ${gift.owner} </strong>" <br>
	<img src="imagedisplay?id=${gift.id}" alt="image is not available" style="width:400px;height:228px"/>
 	Touched by : "<strong> ${gift.touchCount }</strong>" 
 	<form name="voteForm" action="giftdetail?id=${gift.id }" method="POST" > 
 		<input type="submit" name="vote" value="vote for me"/>
 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form><br>
 	<div><a href="giftchain">View Gift Chain</a></div>
 	<div><a href="home">Home</a></div>
</body>
</html>