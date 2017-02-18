package com.server.routing;

import com.server.http.HttpRequestMethod;

import java.util.Map;

public interface ServerRouteConfig {

    Map<HttpRequestMethod, Map<String, RoutingContext>> getRoutes();
}
