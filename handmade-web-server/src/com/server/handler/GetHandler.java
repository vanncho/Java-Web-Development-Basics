package com.server.handler;

import com.server.http.HttpContext;
import com.server.http.response.HttpResponse;

import java.util.function.Function;

public class GetHandler extends RequestHandlerImpl {

    public GetHandler() {
    }

    public GetHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
