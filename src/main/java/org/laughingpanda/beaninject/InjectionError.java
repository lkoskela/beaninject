package org.laughingpanda.beaninject;

@SuppressWarnings("serial")
public class InjectionError extends RuntimeException {
    public InjectionError(String detail, Class<?> dependencyType) {
        super("Injection failed: " + detail.trim() + " for an object of type "
                + dependencyType.getName());
    }
}
