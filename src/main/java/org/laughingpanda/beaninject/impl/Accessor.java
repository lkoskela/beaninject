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
package org.laughingpanda.beaninject.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lasse Koskela
 */
public class Accessor {

    public static List<Method> methods(Class clazz) {
        Method[] declared = clazz.getDeclaredMethods();
        List<Method> methods = new ArrayList<Method>(Arrays
                .asList(declared));
        if (clazz.getSuperclass() != null) {
            methods.addAll(methods(clazz.getSuperclass()));
        }
        return methods;
    }

    public static List<Field> fields(Class clazz) {
        Field[] declared = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList<Field>(Arrays
                .asList(declared));
        if (clazz.getSuperclass() != null) {
            fields.addAll(fields(clazz.getSuperclass()));
        }
        return fields;
    }

    public static Field field(String name, Class clazz) {
        for (Field field : fields(clazz)) {
            if (field.getName().equals(name)) {
                return makeAccessible(field);
            }
        }
        return null;
    }

    private static Field makeAccessible(Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    public static List<Field> fieldsAssignableFrom(Class<?> type,
            Class<?> target) {
        List<Field> fields = new ArrayList<Field>();
        for (Field field : fields(target)) {
            if (field.getType().isAssignableFrom(type)) {
                fields.add(field);
            }
        }
        return fields;
    }

}
