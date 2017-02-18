package com.server.routing;

import com.server.handler.RequestHandlerImpl;

import java.util.List;
import java.util.Map;

public class RoutingContextImpl implements RoutingContext {

    private ControllerActionPair controllerActionPair;
    private RequestHandlerImpl handler;
    private Map<Integer, Class> args;

    public RoutingContextImpl(RequestHandlerImpl handler, ControllerActionPair controllerActionPair, Map<Integer, Class> args) {

        this.handler = handler;
        this.controllerActionPair = controllerActionPair;
        this.args = args;
    }

    @Override
    public RequestHandlerImpl getHandler() {

        return this.handler;
    }

    @Override
    public Map<Integer, Class> getArgumentsMapping() {

        return this.args;
    }

    @Override
    public ControllerActionPair getActionPair() {

        return this.controllerActionPair;
    }
}
