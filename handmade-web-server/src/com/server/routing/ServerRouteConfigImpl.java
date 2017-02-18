package com.server.routing;

import com.server.handler.GetHandler;
import com.server.handler.PostHandler;
import com.server.handler.RequestHandlerImpl;
import com.server.http.HttpRequestMethod;
import com.server.parser.ControllerAnnotationParser;
import com.server.provider.ClassProvider;

import java.util.HashMap;
import java.util.Map;

public class ServerRouteConfigImpl implements ServerRouteConfig {

    private final Map<HttpRequestMethod, Map<String, RoutingContext>> routes;
    private final ClassProvider classProvider;

    public ServerRouteConfigImpl(ClassProvider classProvider) throws IllegalAccessException, InstantiationException {

        this.classProvider = classProvider;
        this.routes = new HashMap<>();

        for (HttpRequestMethod httpRequestMethod : HttpRequestMethod.values()) {

            this.routes.put(httpRequestMethod, new HashMap<>());
        }

        this.initializeServerConfig();
    }

    @Override
    public Map<HttpRequestMethod, Map<String, RoutingContext>> getRoutes() {

        return this.routes;
    }

    private void initializeServerConfig() throws InstantiationException, IllegalAccessException {

        Map<HttpRequestMethod, Map<String, ControllerActionPair>> annotationRoutes = new HashMap<>();

        ControllerAnnotationParser annotationParser = new ControllerAnnotationParser(this.classProvider);
        annotationParser.parse(annotationRoutes);

        for (Map.Entry<HttpRequestMethod, Map<String, ControllerActionPair>> mapEntry : annotationRoutes.entrySet()) {

            for (Map.Entry<String,ControllerActionPair> actionPairEntry : mapEntry.getValue().entrySet()) {

                RequestHandlerImpl requestHandler;

                if (mapEntry.getKey() == HttpRequestMethod.GET) {

                    requestHandler = new GetHandler();
                } else {

                    requestHandler = new PostHandler();
                }

                Map<Integer, Class> args = actionPairEntry.getValue().getArgumentsMapping();
                ControllerActionPair actionPair = actionPairEntry.getValue();

                RoutingContext routingContext = new RoutingContextImpl(requestHandler, actionPair, args);

                this.routes.get(mapEntry.getKey()).put(actionPairEntry.getKey(), routingContext);
            }
        }
    }
}
