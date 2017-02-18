package com.app.view;

import com.server.Model;
import com.server.View;

import java.util.List;

public class HomeListView implements View {

    private final Model model;

    public HomeListView(Model model) {

        this.model = model;
    }

    @Override
    public String view() {

        StringBuilder response = new StringBuilder();

        response.append("<html><body><ul>");

        @SuppressWarnings("unchecked")
        List<String> elements = (List<String>) model.get("list");

        for (String element : elements) {

            if ("".equals(element)) {

                continue;
            }

            response.append("<li>");
            response.append(element);
            response.append("</li>");
        }

        response.append("</ul><form method=\"post\">");
        response.append("<input type=\"text\" name=\"new-item\"/>");
        response.append("<input type=\"submit\" name=\"Add\"/></br></br>");
        response.append("<input type=\"submit\" value=\"Logout\" name=\"logout\"/>");
        response.append("</form></body></html>");
        return response.toString();
    }
}
