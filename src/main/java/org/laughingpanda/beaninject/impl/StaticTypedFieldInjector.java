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
import java.util.List;

import org.laughingpanda.beaninject.InjectionError;

/**
 * @author Lasse Koskela
 */
public class StaticTypedFieldInjector extends AbstractObjectInjector {
    private Class<?> targetClass;

    public StaticTypedFieldInjector(Class<?> targetClass) {
        super(targetClass, null);
        this.targetClass = targetClass;
    }

    public void with(Object dependency) {
        List<Field> fields = collectMatchingStaticFieldsFrom(dependency
                .getClass());
        if (fields.isEmpty()) {
            throw new InjectionError("no matching static field", dependency
                    .getClass());
        }
        if (fields.size() > 1) {
            throw new InjectionError(
                    "ambiguous injection with multiple equal candidates",
                    dependency.getClass());
        }
        inject(dependency, fields.get(0));
    }

    private List<Field> collectMatchingStaticFieldsFrom(Class<?> dependencyType) {
        List<Field> fields = Accessor.fieldsOfType(dependencyType, targetClass);
        addFieldsOfSuperclassType(fields, dependencyType);
        addFieldsOfInterfaceType(fields, dependencyType);
        addFieldsOfTypeJavaLangObject(fields);
        return fields;
    }

    private void addFieldsOfTypeJavaLangObject(List<Field> fields) {
        fields.addAll(Accessor.fieldsOfType(Object.class, targetClass));
    }

    private void addFieldsOfInterfaceType(List<Field> fields,
            Class<?> dependencyType) {
        for (Class<?> implementedInterface : dependencyType.getInterfaces()) {
            if (fields.isEmpty()) {
                fields.addAll(Accessor.fieldsOfType(implementedInterface,
                        targetClass));
            }
        }
    }

    private void addFieldsOfSuperclassType(List<Field> fields,
            Class<?> dependencyType) {
        while (fields.isEmpty()
                && dependencyType.getSuperclass() != Object.class) {
            dependencyType = dependencyType.getSuperclass();
            fields.addAll(Accessor.fieldsOfType(dependencyType, targetClass));
        }
    }

    private void inject(Object dependency, Field field) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(null, dependency);
        } catch (Exception e) {
            throw new RuntimeException("Failure to inject to static field "
                    + field.getName(), e);
        }
    }
}