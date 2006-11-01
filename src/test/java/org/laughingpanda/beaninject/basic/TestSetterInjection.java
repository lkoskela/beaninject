/*
 * Copyright 2006 Lasse Koskela
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laughingpanda.beaninject.basic;

import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.Child;

/**
 * @author Lasse Koskela
 */
public class TestSetterInjection {

    private Object target;

    private String stringValue;

    private int intValue;

    @Before
    public void setUp() throws Exception {
        target = new Child();
        stringValue = "value";
        intValue = 123;
    }

    @Test
    public void privateDeclaredProperty() throws Exception {
        injectAndAssert("privateDeclaredProperty", stringValue);
    }

    @Test
    public void protectedDeclaredProperty() throws Exception {
        injectAndAssert("protectedDeclaredProperty", stringValue);
    }

    @Test
    public void packagePrivateDeclaredProperty() throws Exception {
        injectAndAssert("packagePrivateDeclaredProperty", stringValue);
    }

    @Test
    public void publicDeclaredProperty() throws Exception {
        injectAndAssert("publicDeclaredProperty", stringValue);
    }

    @Test
    public void privateInheritedProperty() throws Exception {
        injectAndAssert("privateInheritedProperty", stringValue);
    }

    @Test
    public void protectedInheritedProperty() throws Exception {
        injectAndAssert("protectedInheritedProperty", stringValue);
    }

    @Test
    public void packagePrivateInheritedProperty() throws Exception {
        injectAndAssert("packagePrivateInheritedProperty",
                stringValue);
    }

    @Test
    public void publicInheritedProperty() throws Exception {
        injectAndAssert("publicInheritedProperty", stringValue);
    }

    @Test
    public void privateInheritedPrimitiveProperty() throws Exception {
        injectAndAssert("privateInheritedPrimitiveProperty", intValue);
    }

    @Test
    public void privateDeclaredPrimitiveProperty() throws Exception {
        injectAndAssert("privateDeclaredPrimitiveProperty", intValue);
    }

    private void injectAndAssert(String propertyName, Object value)
            throws Exception {
        Inject.property(propertyName).of(target).with(value);
        String fieldName = "the"
                + propertyName.substring(0, 1).toUpperCase()
                + propertyName.substring(1);
        assertEquals(value, readField(fieldName, target));
    }

    private Object readField(String name, Object target)
            throws Exception {
        return Accessor.field(name, target.getClass()).get(target);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestSetterInjection.class);
    }
}
