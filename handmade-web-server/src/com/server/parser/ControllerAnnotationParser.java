package com.server.parser;

import com.server.http.HttpRequestMethod;
import com.server.provider.ClassProvider;
import com.server.routing.Controller;
import com.server.routing.ControllerActionPair;
import com.server.routing.RequestMapping;
import com.server.routing.UriParameter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class ControllerAnnotationParser implements AnnotationParser<HttpRequestMethod, Map<String, ControllerActionPair>> {

    private final ClassProvider classProvider;

    public ControllerAnnotationParser(ClassProvider classProvider) {
        
        this.classProvider = classProvider;
    }

    @Override
    public void parse(Map<HttpRequestMethod, Map<String, ControllerActionPair>> routes) throws IllegalAccessException, InstantiationException {

        Class[] classes = this.classProvider.getClassesByAnnotations(Controller.class);

        for (Class currClass : classes) {

            for (Method currMethod : Arrays.stream(currClass.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(RequestMapping.class)).toArray(Method[]::new)) {

                RequestMapping requestMapping = currMethod.getAnnotation(RequestMapping.class);
                HttpRequestMethod requestMethod = requestMapping.method();
                String url = requestMapping.value();
                String[] splittedTokens = url.split("/");
                List<String> urlTokens = Arrays.asList(splittedTokens);
                Map<Integer, Class> argumentMapping = new HashMap<>();

                url = this.createMappingRegex(currMethod, url, urlTokens, argumentMapping);

                Object controllerInstance = currClass.newInstance();

                ControllerActionPair controllerActionPair = new ControllerActionPair(currMethod, controllerInstance, argumentMapping);

                if (!routes.containsKey(requestMethod)) {

                    routes.put(requestMethod, new HashMap<>());
                }

                routes.get(requestMethod).put(url, controllerActionPair);
            }

        }
    }

    private String createMappingRegex(Method currentMethod, String mapping, List<String> mappingTokens, Map<Integer, Class> argumentMapping) {

        for (int i = 0; i < mappingTokens.size(); i++) {

            if (!(mappingTokens.get(i).startsWith("{") && mappingTokens.get(i).endsWith("}"))) {

                continue;
            }

            for (Parameter parameter : currentMethod.getParameters()) {

                if (!parameter.isAnnotationPresent(UriParameter.class)) {

                    continue;
                }

                UriParameter uriParameter = parameter.getAnnotation(UriParameter.class);

                if (mappingTokens.get(i).equals("{" + uriParameter.value() + "}")) {

                    argumentMapping.put(i, parameter.getType());

                    String regexReplacement = null;

                    if (parameter.getType() == String.class) {

                        regexReplacement = "[a-zA-Z]+";
                    } else {

                        regexReplacement = "[0-9]+";
                    }

                    mapping = mapping.replace(mappingTokens.get(i), regexReplacement);
                    break;
                }
            }
        }

        return "^" + mapping + "$";
    }
}
