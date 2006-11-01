package org.laughingpanda.beaninject.custom;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.laughingpanda.beaninject.IInjectionStrategy;
import org.laughingpanda.beaninject.Inject;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.TypeClass;

public class TestCustomInjection {

    static final String BEAN_NAME = "beanName";

    private static final Map<String, Object> fakeSpringContext = new HashMap<String, Object>() {
        {
            put(BEAN_NAME, new TypeClass());
        }
    };

    /**
     * An example of a custom injection strategy, which pulls the dependencies
     * from a fake Spring context and locates the target fields by sniffing for
     * an annotation.
     */
    public class SpringInjectionStrategy implements
            IInjectionStrategy {

        public void inject(Object target) {
            List<Field> fields = Accessor.fields(target.getClass());
            for (Field f : fields) {
                if (f.isAnnotationPresent(SpringBean.class)) {
                    SpringBean annotation = f
                            .getAnnotation(SpringBean.class);
                    Inject.field(f.getName()).of(target).with(
                            fakeSpringContext.get(annotation.name()));
                }
            }
        }
    }

    /**
     * This is the target object, containing an annotated field.
     */
    public class SelfInjectingTarget {
        @SpringBean(name = TestCustomInjection.BEAN_NAME)
        public TypeClass injected;

        public SelfInjectingTarget() {
            // use the "with-strategy-bean" syntax in order to use a custom
            // injection strategy
            Inject.with(new SpringInjectionStrategy()).bean(this);
        }
    }

    @Test
    public void testCustomInjection() throws Exception {
        SelfInjectingTarget bean = new SelfInjectingTarget();
        Assert.assertSame(fakeSpringContext.get(BEAN_NAME),
                bean.injected);
    }
}
