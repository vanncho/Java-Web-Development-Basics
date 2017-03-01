<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log In</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/action.css"/>
</head>
<body>
    <jsp:include page="/fragments/menu.jsp"/>
<br/>
<div class="container">
    <div class="row">
        <jsp:include page="/error.jsp"/>
        <div class="jumbotron">
            <form method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Enter username">
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Log In">
                    <a href="/" class="btn btn-primary">Cancel</a>
                </div>
                <div class="form-inline">
                    <a href="${pageContext.request.contextPath}/create-user">Not Register? Click here</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script  src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script  src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script>
    $("#login").addClass("active");
</script>
</body>
</html>
