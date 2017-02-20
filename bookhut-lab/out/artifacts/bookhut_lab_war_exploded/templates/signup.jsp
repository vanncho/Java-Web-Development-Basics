
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Sign Up</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <form method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username"/>
            <label for="password">Password</label>
            <input type="password" name="password" id="password"/>
            <input type="submit" name="signup" value="Sign Up">
        </form>
    </body>
</html>
