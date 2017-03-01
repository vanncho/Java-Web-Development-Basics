<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/action.css"/>
</head>
<body>
    <jsp:include page="/fragments/menu.jsp"/>
<br/>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <form method="post">
                <div class="form-group">
                    <input name="issueName" type="text" class="form-control" value="${issue.issueName}">
                </div>
                <div class="form-group">
                    <select name="status" class="form-control" required>
                        <optgroup label="Status">
                            <option selected="selected">${issue.status}</option>
                            <option value="New">New</option>
                            <option value="Solved">Solved</option>
                        </optgroup>
                    </select>
                </div>
                <div class="form-group">
                    <select name="priority" class="form-control" required>
                        <optgroup label="Priority">
                            <option selected="selected">${issue.priority}</option>
                            <option value="Low">Low</option>
                            <option value="Medium">Medium</option>
                            <option value="High">High</option>
                        </optgroup>
                    </select>
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Edit">
                    <a href="${pageContext.request.contextPath}/issues/issues" class="btn btn-primary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script  src="D:\SoftUni\SpringMVC\Spring-MVC\issue-tracker\web\static\jquery\jquery.min.js"></script>
<script  src="D:\SoftUni\SpringMVC\Spring-MVC\issue-tracker\web\static\bootstrap\js\bootstrap.min.js"></script>
<script>
    $("#issue").addClass("active");
</script>
</body>
</body>
</html>
