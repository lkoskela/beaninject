package org.laughingpanda.beaninject.impl;

import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractMethodInjector extends AbstractObjectInjector {
    public AbstractMethodInjector(Object target, String name) {
        super(target, name);
    }

    public void with(Object dependency) {
        inject(dependency, Accessor.methods(target.getClass()));
    }

    private void inject(Object dependency, List<Method> accessors) {
        for (Method accessor : accessors) {
            if (match(accessor, dependency.getClass())) {
                inject(dependency, accessor);
                return;
            }
        }
        onNoMatchingSetterFoundFor(dependency);
    }

    private boolean match(Method accessor, Class<?> actualType) {
        Class<?>[] parameters = accessor.getParameterTypes();
        if (parameters.length != 1) {
            return false;
        }
        Class<?> expectedType = Autobox.toPrimitive(parameters[0]);
        if (!expectedType.isAssignableFrom(Autobox.toPrimitive(actualType))) {
            return false;
        }
        return match(accessor);
    }

    protected abstract boolean match(Method accessor);

    protected abstract void onNoMatchingSetterFoundFor(Object dependency);

    private void inject(Object dependency, Method accessor) {
        try {
            if (!accessor.isAccessible()) {
                accessor.setAccessible(true);
            }
            accessor.invoke(target, dependency);
        } catch (Exception e) {
            throw new RuntimeException("Failure to inject to method", e);
        }
    }
}
