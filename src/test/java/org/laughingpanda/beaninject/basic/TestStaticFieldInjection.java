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

import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.Child;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/**
 * @author Lasse Koskela
 */
public class TestStaticFieldInjection {

    private static Object privateStaticObject;

    private static int privateStaticInt;

    @Before
    public void setUp() throws Exception {
        privateStaticObject = new Object();
        privateStaticInt = 123;
    }

    @Test
    public void privateStaticObject() throws Exception {
        Object newValue = "new";
        Inject.staticField("privateStaticObject").of(getClass())
                .with(newValue);
        assertEquals(newValue, readField("privateStaticObject",
                getClass()));
    }

    @Test
    public void privateStaticPrimitive() throws Exception {
        int newValue = 456;
        Inject.staticField("privateStaticInt").of(getClass()).with(
                newValue);
        assertEquals(newValue, readField("privateStaticInt",
                getClass()));
    }

    private Object readField(String name, Class target)
            throws Exception {
        return Accessor.field(name, target).get(target);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestStaticFieldInjection.class);
    }
}
