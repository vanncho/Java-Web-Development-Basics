<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">SoftUni Store</h1></div>

                <form class="form-inline">
                    Filter:
                    <input type="submit" name="filter" class="btn btn-link" value="All"/>
                    <input type="submit" name="filter" class="btn btn-link" value="Owned"/>
                </form>

                <div class="card-group">
                    <c:forEach var="game" items="${homeGames}">
                    <div class="card col-4 thumbnail">

                        <img
                                class="card-image-top img-fluid img-thumbnail"
                                onerror="this.src='https://i.ytimg.com/vi/BqJyluskTfM/maxresdefault.jpg';"
                                src=${game.imageThumbnail}>

                        <div class="card-block">
                            <h4 class="card-title">${game.title}</h4>
                            <p class="card-text"><strong>Price</strong> - ${game.price}&euro;</p>
                            <p class="card-text"><strong>Size</strong> - ${game.size} GB</p>
                            <c:choose>
                                <c:when test="${fn:length(game.description) < 100}">
                                    <p class="card-text">${game.description}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-text">${game.description.substring(0,99)}</p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="card-footer">
                            <c:choose>
                                <c:when test="${sessionScope.loggedUser.role == 'ADMIN'}">
                                    <a class="card-button btn btn-warning" name="edit" href="/edit/${game.id}">Edit</a>
                                    <a class="card-button btn btn-danger" name="delete" href="/delete/${game.id}">Delete</a>
                                </c:when>
                            </c:choose>

                            <a class="card-button btn btn-outline-primary" name="info" href="/details/${game.id}">Info</a>
                            <a class="card-button btn btn-primary" name="buy" href="/cart/${game.id}">Buy</a>
                        </div>

                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</main>
<br/>