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
package org.laughingpanda.beaninject;


import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.Child;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class TestFieldInjection {

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
    public void privateDeclaredField() throws Exception {
        injectAndAssert("privateDeclaredField", stringValue);
    }

    @Test
    public void protectedDeclaredField() throws Exception {
        injectAndAssert("protectedDeclaredField", stringValue);
    }

    @Test
    public void packagePrivateDeclaredField() throws Exception {
        injectAndAssert("packagePrivateDeclaredField", stringValue);
    }

    @Test
    public void publicDeclaredField() throws Exception {
        injectAndAssert("publicDeclaredField", stringValue);
    }

    @Test
    public void privateInheritedField() throws Exception {
        injectAndAssert("privateInheritedField", stringValue);
    }

    @Test
    public void protectedInheritedField() throws Exception {
        injectAndAssert("protectedInheritedField", stringValue);
    }

    @Test
    public void packagePrivateInheritedField() throws Exception {
        injectAndAssert("packagePrivateInheritedField", stringValue);
    }

    @Test
    public void publicInheritedField() throws Exception {
        injectAndAssert("publicInheritedField", stringValue);
    }

    @Test
    public void privateInheritedPrimitiveField() throws Exception {
        injectAndAssert("privateInheritedPrimitiveField", intValue);
    }

    @Test
    public void privateDeclaredPrimitiveField() throws Exception {
        injectAndAssert("privateDeclaredPrimitiveField", intValue);
    }

    private void injectAndAssert(String field, Object value)
            throws Exception {
        Inject.field(field).of(target).with(value);
        assertEquals(value, readField(field, target));
    }

    private Object readField(String name, Object target)
            throws Exception {
        return Accessor.field(name, target.getClass()).get(target);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestFieldInjection.class);
    }
}
