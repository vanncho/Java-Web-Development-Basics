package com.server.handler;

import com.server.http.HttpContext;
import com.server.http.response.HttpResponse;

import java.util.function.Function;

public class PostHandler extends RequestHandlerImpl {

    public PostHandler() {
    }

    public PostHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
