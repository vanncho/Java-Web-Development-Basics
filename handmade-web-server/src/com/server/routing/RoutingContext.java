package com.server.routing;

import com.server.handler.RequestHandlerImpl;

import java.util.Map;

public interface RoutingContext {

    RequestHandlerImpl getHandler();

    Map<Integer, Class> getArgumentsMapping();

    ControllerActionPair getActionPair();
}
