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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.impl.Accessor;

/**
 * @author Lasse Koskela
 */
public class TestStaticFieldInjection {
    private static class Apple {
    }

    private static class GrannySmith extends Apple {
    }

    private static class DietCoke implements Drinkable {
    }

    private static class PepsiMax implements Drinkable {
    }

    public interface Drinkable {
    }

    public static class ClassWithoutFields {
    }

    @SuppressWarnings("unused")
    public static class ClassWithOneFieldOfTypeJavaLangObject {
        private static Object privateStaticObject;
    }

    @SuppressWarnings("unused")
    public static class ClassWithMultipleFieldsOfSameType {
        private static Object privateStaticObject;
        private static Object anotherPrivateStaticObject;
        private static PepsiMax privateStaticPepsiMax;
        private static PepsiMax anotherPrivateStaticPepsiMax;
    }

    @SuppressWarnings("unused")
    public static class ClassWithMultipleFieldsOfSameInterface {
        private static Drinkable privateStaticDrinkable;
        private static Drinkable anotherPrivateStaticDrinkable;
    }

    @SuppressWarnings("unused")
    public static class ClassWithOneFieldOfEachNonRelatedType {
        private static int privateStaticInt;
        private static Apple privateStaticApple;
        private static Drinkable privateStaticDrinkable;
    }

    @Before
    public void setUp() throws Exception {
        resetStaticFieldsOf(ClassWithoutFields.class);
        resetStaticFieldsOf(ClassWithMultipleFieldsOfSameType.class);
        resetStaticFieldsOf(ClassWithMultipleFieldsOfSameInterface.class);
        resetStaticFieldsOf(ClassWithOneFieldOfEachNonRelatedType.class);
    }

    private void resetStaticFieldsOf(Class<?> klass) {
        for (Field field : Accessor.fields(klass)) {
            if ((field.getModifiers() | Modifier.STATIC) == Modifier.STATIC) {
                Inject.staticField(field.getName()).of(klass).with(null);
            }
        }
    }

    @Test
    public void privateStaticObjectByName() throws Exception {
        Object newValue = "new";
        Class<?> targetClass = ClassWithMultipleFieldsOfSameType.class;
        Inject.staticField("privateStaticObject").of(targetClass)
                .with(newValue);
        assertEquals(newValue, readField("privateStaticObject", targetClass));
    }

    @Test
    public void privateStaticPrimitiveByName() throws Exception {
        int newValue = 456;
        Class<?> targetClass = ClassWithOneFieldOfEachNonRelatedType.class;
        Inject.staticField("privateStaticInt").of(targetClass).with(newValue);
        assertEquals(newValue, readField("privateStaticInt", targetClass));
    }

    @Test
    public void privateStaticAppleByExactType() throws Exception {
        Object newValue = new Apple();
        Class<?> targetClass = ClassWithOneFieldOfEachNonRelatedType.class;
        Inject.staticFieldOf(targetClass).with(newValue);
        assertEquals(newValue, readField("privateStaticApple", targetClass));
    }

    @Test
    public void privateStaticAppleBySubType() throws Exception {
        Object newValue = new GrannySmith();
        Class<?> targetClass = ClassWithOneFieldOfEachNonRelatedType.class;
        Inject.staticFieldOf(targetClass).with(newValue);
        assertEquals(newValue, readField("privateStaticApple", targetClass));
    }

    @Test
    public void privateStaticAppleByInterface() throws Exception {
        Object newValue = new DietCoke();
        Class<?> targetClass = ClassWithOneFieldOfEachNonRelatedType.class;
        Inject.staticFieldOf(targetClass).with(newValue);
        assertEquals(newValue, readField("privateStaticDrinkable", targetClass));
    }

    @Test
    public void anythingCanBeInjectedIntoAFieldOfTypeObject() throws Exception {
        Object newValue = new DietCoke();
        Class<?> targetClass = ClassWithOneFieldOfTypeJavaLangObject.class;
        Inject.staticFieldOf(targetClass).with(newValue);
        assertEquals(newValue, readField("privateStaticObject", targetClass));
    }

    @Test
    public void ambiguousInjectionFailsForClassWithMultipleFieldsOfSameClassType() {
        try {
            Class<?> targetClass = ClassWithMultipleFieldsOfSameType.class;
            Inject.staticFieldOf(targetClass).with(new PepsiMax());
            fail();
        } catch (Exception expected) {
            assertTrue(expected.getMessage().toLowerCase()
                    .contains("ambiguous"));
        }
    }

    @Test
    public void ambiguousInjectionFailsForClassWithMultipleFieldsOfSameInterfaceType() {
        try {
            Class<?> targetClass = ClassWithMultipleFieldsOfSameInterface.class;
            Inject.staticFieldOf(targetClass).with(new PepsiMax());
            fail();
        } catch (InjectionError expected) {
            assertTrue(expected.getMessage().toLowerCase()
                    .contains("ambiguous"));
        }
    }

    @Test
    public void injectionFailsIfNoFieldsMatchesAtAll() {
        try {
            Inject.staticFieldOf(ClassWithoutFields.class).with(new PepsiMax());
            fail();
        } catch (InjectionError expected) {
            assertTrue(expected.getMessage().toLowerCase().contains(
                    "no matching static field"));
        }
    }

    private Object readField(String name, Class<?> target) throws Exception {
        return Accessor.field(name, target).get(target);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestStaticFieldInjection.class);
    }
}
