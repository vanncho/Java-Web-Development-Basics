<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Book</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <c:set var="book" value="${VIEW_BOOK_MODELS}"></c:set>
        <form method="post">
            <label for="title">Title</label>
            <input type="text" name="title" id="title" value="${book.title}"/>
            <label for="author">Author</label>
            <input type="text" name="author" id="author" value="${book.author}"/>
            <label for="pages">Pages</label>
            <input type="text" name="pages" id="pages" value="${book.pages}"/>
            <input type="submit" name="edit" value="Edit">
            <input type="submit" name="cancel" value="Cancel">
        </form>
    </body>
</html>
