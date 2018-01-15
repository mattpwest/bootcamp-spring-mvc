<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
</head>

<body>
<h1>Publisher Report</h1>

<c:forEach items="${reports}" var="report">
    <h2><a href="<c:url value="/${report.publisher.id}"/>">${report.publisher.longName}</a></h2>
    <p>
        <strong>Total Heroes: ${report.count}</strong>
    </p>
    <p>
        <a href="<c:url value="/${report.publisher.id}/filter?gender=Female"/>">Female</a>: ${report.female}
    </p>
    <p>
        <a href="<c:url value="/${report.publisher.id}/filter?gender=Male"/>">Male</a>: ${report.male}
    </p>
    <p>
        <a href="<c:url value="/${report.publisher.id}/filter?gender=Other"/>">Other</a>: ${report.other}
    </p>
</c:forEach>

</body>

</html>