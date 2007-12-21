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

import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.annotation.AnnotatedClass;
import org.laughingpanda.beaninject.target.annotation.MyPackagePrivateFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPackagePrivateSetterAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPrivateFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPrivateSetterAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyProtectedFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyProtectedSetterAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPublicFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPublicSetterAnnotation;

/**
 * @author Lasse Koskela
 */
public class TestAnnotationInjection {

    private Object target;

    private String injectedValue;

    @Before
    public void setUp() throws Exception {
        target = new AnnotatedClass();
        injectedValue = "value";
    }

    @Test
    public void forPrivateFields() throws Exception {
        Inject.fieldAnnotatedWith(MyPrivateFieldAnnotation.class).of(
                target).with(injectedValue);
        assertFieldWasSet("annotatedPrivateField");
    }

    @Test
    public void forPackagePrivateFields() throws Exception {
        Inject.fieldAnnotatedWith(
                MyPackagePrivateFieldAnnotation.class).of(target)
                .with(injectedValue);
        assertFieldWasSet("annotatedPackagePrivateField");
    }

    @Test
    public void forProtectedFields() throws Exception {
        Inject.fieldAnnotatedWith(MyProtectedFieldAnnotation.class)
                .of(target).with(injectedValue);
        assertFieldWasSet("annotatedProtectedField");
    }

    @Test
    public void forPublicFields() throws Exception {
        Inject.fieldAnnotatedWith(MyPublicFieldAnnotation.class).of(
                target).with(injectedValue);
        assertFieldWasSet("annotatedPublicField");
    }

    @Test
    public void forPrivateSetters() throws Exception {
        Inject.propertyAnnotatedWith(MyPrivateSetterAnnotation.class)
                .of(target).with(injectedValue);
        assertFieldWasSet("propertyWithPrivateSetter");
    }

    @Test
    public void forPackagePrivateSetters() throws Exception {
        Inject.propertyAnnotatedWith(
                MyPackagePrivateSetterAnnotation.class).of(target)
                .with(injectedValue);
        assertFieldWasSet("propertyWithPackagePrivateSetter");
    }

    @Test
    public void forProtectedSetters() throws Exception {
        Inject.propertyAnnotatedWith(
                MyProtectedSetterAnnotation.class).of(target).with(
                injectedValue);
        assertFieldWasSet("propertyWithProtectedSetter");
    }

    @Test
    public void forPublicSetters() throws Exception {
        Inject.propertyAnnotatedWith(MyPublicSetterAnnotation.class)
                .of(target).with(injectedValue);
        assertFieldWasSet("propertyWithPublicSetter");
    }

    private void assertFieldWasSet(String name) throws Exception {
        assertEquals(injectedValue, readField(name, target));
    }

    private Object readField(String name, Object target)
            throws Exception {
        return Accessor.field(name, target.getClass()).get(target);
    }
}
