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

import org.laughingpanda.beaninject.impl.FieldInjector;
import org.laughingpanda.beaninject.impl.MethodInjector;
import org.laughingpanda.beaninject.impl.TypeBasedInjector;

/**
 * Utility for injecting dependencies to Java objects using the Reflection API.
 * <p>
 * Supported injection strategies include:
 * <ul>
 * <li>Setter injection by property name</li>
 * <li>Field injection by field name</li>
 * <li>Setter injection by dependency type</li>
 * <li>Field injection by dependency type</li>
 * </ul>
 * 
 * @author Lasse Koskela
 */
public class Inject {

    /**
     * Returns a target identifier that uses an injector that injects directly
     * to a member field regardless of the field's visibility.
     * 
     * @param fieldName
     *            The name of the field to inject.
     */
    public static ITargetIdentifier field(final String fieldName) {
        return new ITargetIdentifier() {

            public IObjectInjector of(final Object target) {
                return new FieldInjector(target, fieldName);
            }
        };
    }

    /**
     * Returns a target identifier that uses an injector that injects through a
     * property setter method (regardless of the method's visibility).
     * 
     * @param propertyName
     *            The name of the property to inject. E.g. "foo" where the
     *            respective setter method's name is "setFoo".
     */
    public static ITargetIdentifier property(final String propertyName) {
        return new ITargetIdentifier() {

            public IObjectInjector of(Object target) {
                String methodName = "set"
                        + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                return new MethodInjector(target, methodName);
            }
        };
    }

    /**
     * Returns an injector implementation which uses the given dependency
     * object's type to infer which setter/field to inject.
     * 
     * @param target
     *            The target object for injection.
     */
    public static IObjectInjector bean(final Object target) {
        return new IObjectInjector() {

            public void with(Object dependency) {
                new TypeBasedInjector().inject(target, dependency);
            }
        };
    }
}