<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container">
        <div class="row">
            <h2 class="m-1">All Games &ndash;&nbsp;</h2> <a href="${pageContext.request.contextPath}/add-game" class="btn btn-warning m-1"><strong>+</strong> Add
            Game</a>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Size</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="game" items="${games}">
                    <c:set var="count" value="0" />
                <tr class="table-warning">
                    <th scope="row">${count + 1}</th>
                    <td>${game.title}</td>
                    <td>${game.size}</td>
                    <td>${game.price} &euro;</td>
                    <td>
                        <a href="/edit/${game.id}" class="btn btn-warning btn-sm">Edit</a>
                        <a href="/delete/${game.id}" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</main>
