package com.server.provider;

import com.server.util.Constants;
import com.server.util.DirectoryViewer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClassProviderImpl implements ClassProvider {

    private Class[] classes;

    private Map<Class, Class[]> classesByAnnotation;

    public ClassProviderImpl() throws ClassNotFoundException {

        this.classes = DirectoryViewer.findControllers(Constants.INTERNAL_APP_STRING);
        this.classesByAnnotation = new HashMap<>();
    }

    @Override
    public Class[] getClassesByAnnotations(Class annotation) {

        if (this.classesByAnnotation.containsKey(annotation)) {

            return this.classesByAnnotation.get(annotation);
        }

        Class[] result = Arrays.stream(this.classes)
                .filter(c -> c.isAnnotationPresent(annotation))
                .toArray(Class[]::new);

        this.classesByAnnotation.put(annotation, result);

        return result;
    }
}
