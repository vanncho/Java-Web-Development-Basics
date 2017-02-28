<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main>
    <div class="container body-content">
        <div class="row">

            <c:forEach var="article" items="${articles}">
            <div class="col-md-6">
                <article>
                    <header>
                        <h2><c:out value="${article.title}"/></h2>
                    </header>
                        <c:choose>
                            <c:when test="${fn:length(article.content) < 500}">
                                <p class="article-content"> <c:out value="${article.content}"/></p>
                            </c:when>
                            <c:otherwise>
                                <p class="article-content"> <c:out value="${article.content.substring(0, 499)}"/></p>
                            </c:otherwise>
                        </c:choose>
                    <small class="author"><c:out value="${article.author.fullName}"/></small>

                    <footer>
                        <div class="pull-right">
                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/article/details-article/${article.id}">Read more &raquo;</a>
                        </div>
                    </footer>
                </article>
            </div>
            </c:forEach>

        </div>
    </div>
</main>