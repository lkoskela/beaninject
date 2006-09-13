package org.laughingpanda.beaninject.example;

import org.junit.Assert;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;

public class TestExample {

    public class Target {
        private String property;

        public void setProperty(String value) {
            this.property = value;
        }

        public String getProperty() {
            return property;
        }
    }

    @Test
    public void exampleOfInjectingWithSetter() throws Exception {
        Target target = new Target();
        Assert.assertNull(target.getProperty());

        Inject.property("property").of(target).with("value");

        Assert.assertEquals("value", target.getProperty());
    }

}
