package org.laughingpanda.beaninject.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotatedMethodInjector extends MethodInjector {

    private final Class<? extends Annotation> annotation;

    public AnnotatedMethodInjector(Object target,
            Class<? extends Annotation> annotation) {
        super(target, null);
        this.annotation = annotation;
    }

    @Override
    protected boolean match(Method accessor) {
        return accessor.isAnnotationPresent(annotation);
    }

    @Override
    protected void onNoMatchingSetterFoundFor(Object dependency) {
        throw new RuntimeException("No method on "
                + target.getClass().getName() + " had annotation @"
                + annotation.getName()
                + " and accepted an argument of type "
                + dependency.getClass().getName());
    }
}
