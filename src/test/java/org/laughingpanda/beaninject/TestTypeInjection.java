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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.TypeClass;
import org.laughingpanda.beaninject.target.TypeClassA;
import org.laughingpanda.beaninject.target.TypeClassParent;
import org.laughingpanda.beaninject.target.TypeInterface;
import org.laughingpanda.beaninject.target.TypeInterfaceImplementation;

/**
 * @author Lasse Koskela
 */
public class TestTypeInjection {

    private Object target;

    protected Object injectedWithSetter;

    @Test
    public void fieldInjectionBasedOnInterfaceType() throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            public TypeInterface field;
        };
        injectAndAssert(new TypeInterfaceImplementation(), "field");
    }

    @Test
    public void fieldInjectionBasedOnClassType() throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            public TypeClass field;
        };
        injectAndAssert(new TypeClass(), "field");
    }

    @Test
    public void methodInjectionBasedOnClassType() throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            private Object field;

            @SuppressWarnings("unused")
            public void setFoo(TypeClass value) {
                field = value;
            }
        };
        injectAndAssert(new TypeClass(), "field");
    }

    @Test
    public void methodInjectionBasedOnInterfaceType()
            throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            private Object field;

            @SuppressWarnings("unused")
            public void setFoo(TypeInterface value) {
                field = value;
            }
        };
        injectAndAssert(new TypeInterfaceImplementation(), "field");
    }

    @Test
    public void methodInjectionIsPreferredOverFieldInjection()
            throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            public TypeClass field;

            @SuppressWarnings("unused")
            public void setFoo(TypeClass value) {
                injectedWithSetter = value;
            }
        };
        Object value = new TypeClass();
        Inject.bean(target).with(value);
        assertNull("Setter should be preferred", readField("field",
                target));
        assertEquals(value, injectedWithSetter);
    }

    @Test
    public void multipleFieldsFromSameHierarchyButOnlyOneAssignableFromValue()
            throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            public TypeClass field;

            @SuppressWarnings("unused")
            public TypeClassA anotherField;
        };
        injectAndAssert(new TypeClass(), "field");
    }

    @Test
    public void multipleFieldsWithExactSameType() throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            public TypeClass field1;

            @SuppressWarnings("unused")
            public TypeClass field2;
        };
        Object value = new TypeClass();
        try {
            Inject.bean(target).with(value);
            fail("Type-based injection should fail if there "
                    + "are multiple fields with the same type");
        } catch (Exception expected) {
            assertEquals("Multiple fields of matching type: "
                    + value.getClass().getName(), expected
                    .getMessage());
        }
        assertNull(readField("field1", target));
        assertNull(readField("field1", target));
    }

    @Test
    public void noFieldsWithMatchingType() throws Exception {
        target = new Object();
        Object value = new TypeClass();
        try {
            Inject.bean(target).with(value);
            fail("Type-based injection should fail if there "
                    + "are no fields with the given type");
        } catch (Exception expected) {
            assertEquals("No field of matching type: "
                    + value.getClass().getName(), expected
                    .getMessage());
        }
    }

    @Test
    public void multipleFieldsOfDifferentTypeButAssignableFromValue()
            throws Exception {
        target = new Object() {
            @SuppressWarnings("unused")
            public TypeClassParent field1;

            @SuppressWarnings("unused")
            public TypeClass field2;
        };
        Object value = new TypeClassA();
        try {
            Inject.bean(target).with(value);
            fail("Type-based injection should fail if there "
                    + "are multiple fields with the same type");
        } catch (Exception expected) {
            assertEquals("Multiple fields of matching type: "
                    + value.getClass().getName(), expected
                    .getMessage());
        }
        assertNull(readField("field1", target));
        assertNull(readField("field1", target));
    }

    private void injectAndAssert(Object value, String fieldName)
            throws Exception {
        Inject.bean(target).with(value);
        assertEquals(value, readField(fieldName, target));
    }

    private Object readField(String name, Object target)
            throws Exception {
        return Accessor.field(name, target.getClass()).get(target);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestTypeInjection.class);
    }
}
