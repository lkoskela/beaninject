package org.laughingpanda.beaninject.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;

public class TestExample {

    private Target target;

    public class Target {
        private String field;

        public void setPropertyName(String value) {
            this.field = value;
        }

        public String getPropertyName() {
            return field;
        }
    }

    @Before
    public void setUp() {
        target = new Target();
        assertNull(target.getPropertyName());
    }

    @Test
    public void exampleOfInjectingWithSetter() throws Exception {
        Inject.property("propertyName").of(target).with("value");
        assertEquals("value", target.getPropertyName());
    }

    @Test
    public void exampleOfInjectingDirectlyToField() throws Exception {
        Inject.field("field").of(target).with("value");
        assertEquals("value", target.getPropertyName());
    }

    @Test
    public void exampleOfTypeBasedInjection() throws Exception {
        Inject.bean(target).with("value");
        assertEquals("value", target.getPropertyName());
    }
}
