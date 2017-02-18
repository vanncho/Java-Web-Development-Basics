package com.server.handler;

import com.server.http.HttpContext;
import com.server.http.response.HttpResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public interface RequestHandler {

    void handle(HttpContext httpContext) throws IOException;

    void setWriter(Writer writer);
}
