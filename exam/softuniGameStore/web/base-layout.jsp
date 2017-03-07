<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>

<c:choose>
    <c:when test="${sessionScope.loggedUser != null}">
        <jsp:include page="fragments/top-nav-logged.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="fragments/top-nav.jsp"/>
    </c:otherwise>
</c:choose>

<jsp:include page="${view}"/>

<jsp:include page="fragments/footer.jsp"/>
<jsp:include page="fragments/script-bundle.jsp"/>
</body>
</html>