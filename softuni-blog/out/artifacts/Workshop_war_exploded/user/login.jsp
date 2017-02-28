<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container body-content span=8 offset=2">

        <jsp:include page="/error.jsp"/>
        <div class="well">

            <form class="form-horizontal" method="post">
                <fieldset>
                    <legend>Login</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-email">Email</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="email" name="email" id="user-email" placeholder="Email" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-password">Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="password" name="password" id="user-password" placeholder="Password" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <input type="submit" name="submit" value="Login" class="btn btn-primary"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</main>
