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

import org.laughingpanda.beaninject.IObjectInjector;

/**
 * @author Lasse Koskela
 */
public abstract class AbstractObjectInjector implements
        IObjectInjector {

    protected Object target;

    protected String name;

    public AbstractObjectInjector(Object target, String name) {
        this.target = target;
        this.name = name;
    }

    public abstract void with(Object dependency);

}
