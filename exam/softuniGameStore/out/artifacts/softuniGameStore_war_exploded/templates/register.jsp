<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="col-4 offset-4 text-center">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">Register</h1></div>
                <br/>
                <jsp:include page="/error.jsp"/>
                <form method="post">

                    <div class="form-group row">
                        <label class="sr-only">Email</label>
                        <input type="email" class="form-control" name="email" placeholder="somebody@example.com"/>
                    </div>

                    <div class="form-group row">
                        <label class="sr-only">Full Name</label>
                        <input pattern="^[a-zA-Z -.]+$" name="fullName" class="form-control" placeholder="Full Name"/>
                    </div>

                    <div class="form-group row">
                        <label class="sr-only">Password</label>
                        <input type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password" class="form-control" placeholder="Password"/>
                    </div>

                    <div class="form-group row">
                        <label class="sr-only">Confirm Password</label>
                        <input type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="confirmPassword" class="form-control" placeholder="Confirm Password"/>
                    </div>

                    <input type="submit" class="btn btn-outline-warning btn-lg btn-block"
                           value="Register"/>
                </form>
                <br/>
            </div>
        </div>
    </div>
</main>