<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/styles.css">

    <!-- Latest compiled and minified JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Comic Report</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/"/>">Home</a></li>
                <li class="active"><a href="#">Publishers <span class="sr-only">(current)</span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<img src="/images/banner_thin.jpg" class="banner"/>

<div class="container">
    <div class="row">
        <h1>Publishers Overview</h1>
    </div>

    <c:forEach items="${reports}" var="report">
    <div class="row">
        <div class="col-md-3">
            <h2>${report.publisher.longName}</h2>
        </div>

        <div class="col-md-2">
            <h2>${report.count} Heroes</h2>
        </div>
        <div class="col-md-2">
            <h2>
                <a href="<c:url value="/report/${report.publisher.id}"/>" class="btn btn-primary btn-filter">View All</a>
            </h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-md-offset-3">
            <p>${report.female} Females</p>
        </div>
        <div class="col-md-2">
            <p><a href="<c:url value="/report/${report.publisher.id}/filter?gender=Female"/>" class="btn btn-xs btn-default btn-filter">View Females</a></p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2 col-md-offset-3">
            <p>${report.male} Males</p>
        </div>
        <div class="col-md-2">
            <p><a href="<c:url value="/report/${report.publisher.id}/filter?gender=Male"/>" class="btn btn-xs btn-default btn-filter">View Males</a></p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2 col-md-offset-3">
            <p>${report.other} Others</p>
        </div>
        <div class="col-md-2">
            <p><a href="<c:url value="/report/${report.publisher.id}/filter?gender=Other"/>" class="btn btn-xs btn-default btn-filter">View Others</a></p>
        </div>
    </div>
    </c:forEach>
</div>

</body>

</html>