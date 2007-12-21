package org.laughingpanda.beaninject.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotatedFieldInjector extends AbstractObjectInjector {

    private final Class<? extends Annotation> annotation;

    public AnnotatedFieldInjector(Object target,
            Class<? extends Annotation> annotation) {
        super(target, null);
        this.annotation = annotation;
    }

    public void with(Object dependency) {
        inject(dependency, Accessor.annotatedField(annotation, target
                .getClass()));
    }

    private void inject(Object dependency, Field field) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(target, dependency);
        } catch (Exception e) {
            throw new RuntimeException("Failure to inject to field "
                    + field.getName(), e);
        }
    }
}
