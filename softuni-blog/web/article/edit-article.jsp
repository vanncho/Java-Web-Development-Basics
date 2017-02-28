<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container body-content span=8 offset=2">

        <jsp:include page="/error.jsp"/>
        <div class="well">

            <form class="form-horizontal" method="post">
                <fieldset>
                    <legend>Edit Article</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="article-title">Article Title</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" name="title" id="article-title" value="${title}" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="article-content">Content</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" rows="7" id="article-content" name="content">${content}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default" href="${pageContext.request.contextPath}/home/index">Cancel</a>
                            <input type="submit" name="edit" value="Submit" class="btn btn-success"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</main>
