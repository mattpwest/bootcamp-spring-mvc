<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
</head>

<body>
<h1>Publisher Report</h1>

<c:forEach items="${reports}" var="report">
    <h2>${report.publisher.longName}</h2>
    <p>
        <strong>Total Heroes: ${report.count}</strong>
    </p>
    <p>
        Female: ${report.female}
    </p>
    <p>
        Male: ${report.male}
    </p>
    <p>
        Other: ${report.other}
    </p>
</c:forEach>

</body>

</html>