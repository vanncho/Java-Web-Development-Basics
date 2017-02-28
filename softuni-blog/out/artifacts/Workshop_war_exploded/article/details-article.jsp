<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="container body-content">
        <div class="row">
            <div class="col-md-12">
                <article>
                    <header>
                        <h2>${title}</h2>
                    </header>

                    <p> ${content} </p>

                    <small class="author">${author}</small>

                    <footer>
                        <div class="pull-right">
                            <a class="btn btn-success btn-xs" href="/article/edit-article/${articleId}">Edit</a>
                            <a class="btn btn-danger btn-xs" href="/article/delete-article/${articleId}">Delete</a>

                            <a class="btn btn-default btn-xs" href="/">back &raquo;</a>
                        </div>
                    </footer>
                </article>
            </div>
        </div>
    </div>
</main>