package com.server.handler;

import com.server.http.HttpContext;
import com.server.http.HttpSession;
import com.server.http.HttpSessionImpl;
import com.server.http.response.HttpResponse;
import com.server.util.SessionCreator;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public abstract class RequestHandlerImpl implements RequestHandler {

    private Function<HttpContext, HttpResponse> function;
    private Writer writer;

    RequestHandlerImpl() {
    }

    RequestHandlerImpl(Function<HttpContext, HttpResponse> function) {

        this.function = function;
    }

    private void setSession(HttpContext httpContext) {

        HttpSession httpSession = httpContext.getHttpRequest().getHttpSession();

        if (httpSession == null) {

            String sessionId = SessionCreator.getInstance().generateSessionId();

            httpSession = new HttpSessionImpl(sessionId);
            httpContext.getHttpRequest().setSession(httpSession);
        }
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {

        this.setSession(httpContext);

        HttpResponse httpResponse = this.function.apply(httpContext);

        if (!httpContext.getHttpRequest().getHttpSession().isSet()) {

            httpResponse.addResponseHeader("Set-Cookie", "sessionId=" + httpContext.getHttpRequest().getHttpSession().getId() + "; HttpOnly");
            httpContext.getHttpRequest().getHttpSession().setSet();
        }

        httpResponse.addResponseHeader("Content-Type", "text/html");
        this.writer.write(httpResponse.getResponse());
    }

    @Override
    public void setWriter(Writer writer) {

        this.writer = writer;
    }

    public void setFunction(Function<HttpContext, HttpResponse> function) {

        this.function = function;
    }
}
