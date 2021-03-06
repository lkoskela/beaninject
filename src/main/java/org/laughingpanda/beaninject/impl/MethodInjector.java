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

import java.lang.reflect.Method;

/**
 * @author Lasse Koskela
 */
public class MethodInjector extends AbstractMethodInjector {

    public MethodInjector(Object target, String name) {
        super(target, name);
    }

    protected void onNoMatchingSetterFoundFor(Object dependency) {
        throw new RuntimeException("No method on "
                + target.getClass().getName() + " matched name \""
                + name + "\" and type "
                + dependency.getClass().getName());
    }

    @Override
    protected boolean match(Method accessor) {
        return accessor.getName().equals(name);
    }
}
