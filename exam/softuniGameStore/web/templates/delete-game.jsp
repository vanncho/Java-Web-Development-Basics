<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="col-4 offset-4 text-center">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">${game.title}</h1></div>
                <br/>
                <form method="post">
                    <div class="form-group row">
                        <label class="form-control-label">Title</label>
                        <input name="title" pattern="[A-Z].{3,100}" class="form-control"
                               placeholder="Enter Game Title" value="${game.title}" disabled />
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Description</label>
                        <textarea disabled name="description" class="form-control" placeholder="Enter Game Description" minlength="20">${game.description}</textarea>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Thumbnail</label>
                        <input name="imageThumbnail" type="url" class="form-control" placeholder="Enter URL to Image" value="${game.imageThumbnail}" disabled />
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Price</label>
                        <div class="input-group">

                            <input name="price" step="0.01" min="0" class="form-control" placeholder="Enter Price" value="${game.price}" disabled />
                            <span class="input-group-addon">&euro;</span>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Size</label>
                        <div class="input-group">

                            <input name="size" step="0.1" min="0" class="form-control" placeholder="Enter Size" value="${game.size}" disabled />
                            <span class="input-group-addon">GB</span>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">YouTube Video URL</label>
                        <div class="input-group">
                            <span class="input-group-addon">https://www.youtube.com/watch?v=</span>
                            <input name="trailer" class="form-control" minlength="11" maxlength="11" value="${game.trailer}" disabled />
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Release Date</label>
                        <input name="releaseDate" type="date" class="form-control" placeholder="yyyy-MM-dd" value="${game.releaseDate}" disabled />
                    </div>

                    <input type="submit" class="btn btn-outline-warning btn-lg btn-block"
                           value="Delete Game"/>
                </form>
                <br/>
            </div>
        </div>
    </div>
</main>