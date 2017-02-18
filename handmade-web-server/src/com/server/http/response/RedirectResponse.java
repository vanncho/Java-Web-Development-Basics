package com.server.http.response;

public class RedirectResponse extends HttpResponseImpl {

    public RedirectResponse(String redirectUrl) {
        super(redirectUrl);
    }
}
