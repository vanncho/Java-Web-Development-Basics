package com.server.http.response;

import com.server.View;

public class ViewResponse extends HttpResponseImpl {

    public ViewResponse(HttpResponseCode responseCode, View view) {
        super(responseCode, view);
    }
}
