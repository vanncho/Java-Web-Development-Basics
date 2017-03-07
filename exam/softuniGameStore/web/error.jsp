<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<c:if test="${errors != null}">
    <c:forEach items="${errors}" var="error">
        <div class="alert alert-dismissible alert-danger">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
                ${error}
        </div>
    </c:forEach>
</c:if>
</div>