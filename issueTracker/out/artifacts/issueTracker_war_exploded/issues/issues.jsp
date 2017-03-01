<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Issues</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/action.css"/>
</head>
<body>
    <jsp:include page="/fragments/menu.jsp"/>
<br/>
<div class="container">
    <form method="get">
        <div class="row">
            <div class="col-sm-2">
                <div class="form-group">
                    <select name="status"  class="form-control" required>
                        <optgroup label="Status">
                            <option value="" disabled hidden selected>Status</option>
                            <option>All</option>
                            <option>New</option>
                            <option>Solved</option>
                        </optgroup>
                    </select>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="form-group">
                    <input name="name" type="text" class="form-control" placeholder="Search">
                </div>
            </div>
            <div class="col-sm-2">
                <button type="submit" class="btn btn-warning">Search</button>
            </div>
        </div>
        <div class="row">
            <a class="btn btn-success" href="/issues/add">Add Issue</a>
        </div>
    </form>
    <div class="row">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Creation Date</th>
                <th>Author</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="isssue" items="${allIssues}">
                <tr>
                    <td><c:out value="${isssue.id}"/></td>
                    <td><c:out value="${isssue.issueName}"/></td>
                    <td><c:out value="${isssue.status}"/></td>
                    <td><c:out value="${isssue.priority}"/></td>
                    <td><c:out value="${isssue.createdOn}"/></td>
                    <td><c:out value="${isssue.author.username}"/></td>
                    <c:choose>
                        <c:when test="${user.role == 'ADMIN' || loggedUser.id == isssue.author.id}">
                    <td>
                        <a href="/issues/edit-issue/${isssue.id}" class="btn mini btn-primary">Edit</a>
                    </td>
                    <td>
                        <a href="/issues/delete/${isssue.id}" class="confirm-delete mini btn btn-danger">Delete</a>
                    </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script  src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script  src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script>
    $("#issues").addClass("active");
</script>
</body>
</html>
