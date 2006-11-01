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

/**
 * An object capable of injecting a given target with previously configured (or
 * dynamically resolved) dependencies.
 * 
 * @author Lasse Koskela
 */
public interface ITargetInjector {

    /**
     * Injects configured dependencies to the given target object.
     * 
     * @param target
     *            The target object to inject with dependencies.
     */
    void bean(Object target);
}