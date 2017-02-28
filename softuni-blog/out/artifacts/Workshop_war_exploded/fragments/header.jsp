<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <!-- <a href="${pageContext.request.contextPath}/home/index.jsp" class="navbar-brand">SOFTUNI BLOG</a> -->
                <a href="${pageContext.request.contextPath}/" class="navbar-brand">SOFTUNI BLOG</a>

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${loggedUser != null}">
                            <li>
                                <a href="${pageContext.request.contextPath}/article/create-article">Create Article</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/user/userprofile">My Profile</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/user/logout">Logout</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${pageContext.request.contextPath}/user/register">
                                    REGISTER
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/user/login">
                                    LOGIN
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</header>