<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/shelves.css">
</head>
<body>
    <jsp:include page="menu.jsp"></jsp:include>
    <table class="tb">
        <thead>
            <th>Title</th>
            <th>Author</th>
            <th>Pages</th>
            <th>Edit Book</th>
            <th>Delete Book</th>
        </thead>
        <tbody>
            <c:set var="books" value="${VIEW_BOOK_MODELS}"/>
            <c:forEach items="${books}" var="book">
            <tr>
                <td><c:out value="${book.title}"></c:out></td>
                <td><c:out value="${book.author}"></c:out></td>
                <td><c:out value="${book.pages}"></c:out></td>
                <td><a href="../shelves/edit/${book.title}">Edit</a></td>
                <td><a href="../shelves/delete/${book.title}">Delete</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
