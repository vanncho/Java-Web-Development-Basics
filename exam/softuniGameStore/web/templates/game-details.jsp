<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">

                <h1 class="display-3">${game.title}</h1>
                <br/>

                <iframe width="560" height="315" src="https://www.youtube.com/embed/${game.trailer}" frameborder="0"
                        allowfullscreen></iframe>

                <br/>
                <br/>

                <p>${game.description}</p>

                <p><strong>Price</strong> - ${game.price}&euro;</p>
                <p><strong>Size</strong> - ${game.size}</p>
                <p><strong>Release Date</strong> - ${game.releaseDate}</p>

                <a class="btn btn-outline-primary" href="/">Back</a>
                <c:choose>
                    <c:when test="${sessionScope.loggedUser.role == 'ADMIN'}">
                        <a class="card-button btn btn-warning" name="edit" href="/edit/${game.id}">Edit</a>
                        <a class="card-button btn btn-danger" name="delete" href="/delete/${game.id}">Delete</a>
                    </c:when>
                </c:choose>
                <a class="btn btn-primary" href="/cart/${game.id}">Buy</a>

                <br/>
                <br/>

            </div>
        </div>
    </div>
</main>
<br/>
