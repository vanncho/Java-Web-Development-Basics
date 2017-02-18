package com.server.routing;

import java.lang.reflect.Method;
import java.util.Map;

public class ControllerActionPair {

    private final Method action;
    private final Object controller;
    private final Map<Integer, Class> argumentsMapping;

    public ControllerActionPair(Method action, Object controller, Map<Integer, Class> argumentsMapping) {

        this.action = action;
        this.controller = controller;
        this.argumentsMapping = argumentsMapping;
    }

    public Method getAction() {

        return this.action;
    }

    public Object getController() {
        return this.controller;
    }

    public Map<Integer, Class> getArgumentsMapping() {
        return this.argumentsMapping;
    }
}
