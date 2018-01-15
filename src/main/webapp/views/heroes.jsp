<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
</head>

<body>
<a href="<c:url value="/"/>">[Home]</a>

<h1>Publisher: ${publisher.longName}</h1>
<c:if test="${filter != null}">
    <p>Filtered for: ${filter} <a href="<c:url value="/${publisher.id}"/>">[Clear Filter]</a></p>
</c:if>

<ul>
<c:forEach items="${heroes}" var="hero">
    <li>${hero.name} (${hero.gender})</li>
</c:forEach>
</ul>

</body>

</html>