<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.models.bindingModels.LoginModel" %>
<%@ page import="com.config.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Menu</title>
        <link rel="stylesheet" href="/css/menu.css">
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/signup">SignUp</a></li>
                <%
                    LoginModel loginModel = (LoginModel) session.getAttribute(Config.LOGIN_MODEL);

                    if (loginModel != null) {

                        String username = loginModel.getUsername();
                        request.setAttribute(Config.USERNAME, username);
                    }
                %>
                <c:set var="username" value="${USERNAME}"></c:set>
                <c:choose>
                    <c:when test="${username != null}">
                        <li><a href="/signout">SignOut(${username})</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/signin">SignIn</a></li>
                    </c:otherwise>
                </c:choose>


                <li><a href="/add">Add Book</a></li>
                <li><a href="/shelves">Shelves</a></li>
            </ul>
        </nav>
    </body>
</html>
