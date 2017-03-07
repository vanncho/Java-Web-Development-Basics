<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">Your Cart</h1></div>
                <br/>
                <jsp:include page="/error.jsp"/>
                <div class="list-group">
                    <c:forEach var="game" items="${gamesInCart.entrySet()}">
                        <div class="list-group-item">
                            <a class="btn btn-outline-danger btn-lg" href="/remove-game/${game.value.title}">X</a>
                            <div class="media col-3">
                                <figure class="pull-left">
                                    <a href="#">
                                        <img
                                                class="card-image-top img-fluid img-thumbnail"
                                                onerror="this.src='https://i.ytimg.com/vi/BqJyluskTfM/maxresdefault.jpg';"
                                                src="${game.value.imageThumbnail}"></a>
                                </figure>
                            </div>
                            <div class="col-md-6">
                                <a href="#"><h4 class="list-group-item-heading"> ${game.value.title} </h4></a>
                                <c:choose>
                                    <c:when test="${fn:length(game.value.description) < 100}">
                                        <p class="list-group-item-text">${game.value.description}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="list-group-item-text">${game.value.description.substring(0,99)}</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-2 text-center mr-auto">
                                <h2> ${game.value.price}&euro; </h2>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <br/>
                <div class="col-8 offset-2 my-3 text-center">
                    <h1><strong>Total Price - </strong>
                        <c:choose>
                            <c:when test="${sumOfGames == null}">
                        0 &euro;</h1>
                            </c:when>
                    <c:otherwise>
                        ${sumOfGames} &euro;</h1>
                    </c:otherwise>
                        </c:choose>
                </div>
                <div class="col-8 offset-2 my-3">
                    <form>

                        <input type="submit" class="btn btn-success btn-lg btn-block"
                               value="Order"/>
                    </form>
                </div>
                <br/>
            </div>
        </div>
    </div>
</main>