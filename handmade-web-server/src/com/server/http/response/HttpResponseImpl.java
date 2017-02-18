package com.server.http.response;

import com.server.View;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpResponseImpl implements HttpResponse {

    private final HttpResponseCode responseCode;
    private View template;
    private Map<String, String> responseHeaders;

    HttpResponseImpl(String redirectUrl) {

        this.responseHeaders = new HashMap<>();
        this.responseCode = HttpResponseCode.MovedPermanently;
        this.addResponseHeader("Location", redirectUrl);
    }

    HttpResponseImpl(HttpResponseCode responseCode, View view) {

        this.responseHeaders = new HashMap<>();
        this.responseCode = responseCode;
        this.template = view;
    }

    @Override
    public String getResponse() {

        StringBuilder response = new StringBuilder();

        response.append(String.format("HTTP/1.1 %d %s%n", this.responseCode.getValue(), this.responseCode.getText()));

        for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {

            response.append(String.format("%s: %s%n", entry.getKey(), entry.getValue()));
        }

        response.append(System.lineSeparator());

        if (this.responseCode != HttpResponseCode.MovedPermanently) {

            response.append(this.template.view());
        }

        return response.toString();
    }

    @Override
    public void addResponseHeader(String header, String value) {

        this.responseHeaders.put(header, value);
    }
}
