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

/**
 * @author Lasse Koskela
 */
public class FieldInjector extends AbstractObjectInjector {
	public FieldInjector(Object target, String name) {
		super(target, name);
	}

	public void with(Object dependency) {
		inject(dependency, Accessor.field(name, target.getClass()));
	}

	private void inject(Object dependency, Field accessor) {
		try {
			if (!accessor.isAccessible()) {
				accessor.setAccessible(true);
			}
			accessor.set(target, dependency);
		} catch (Exception e) {
			throw new RuntimeException("Failure to inject to field", e);
		}
	}
}