package com.app.view;

import com.server.View;

public class HomeIndexView implements View {

    @Override
    public String view() {

        return "<html><body>" +
                "<form method=\"post\">" +
                "Username: <input type=\"text\" name=\"username\"></br>" +
                "<input type=\"submit\" value=\"Login\"/>" +
                "</form>" +
                "</body></html>";
    }
}
