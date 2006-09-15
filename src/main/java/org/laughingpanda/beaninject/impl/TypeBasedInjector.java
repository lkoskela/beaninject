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
import java.util.List;

/**
 * @author Lasse Koskela
 */
public class TypeBasedInjector {

	public void inject(Object target, Object dependency) {
		if (!injectWithSetter(target, dependency)) {
			Field field = getMatchingField(target, dependency);
			inject(target, dependency, field);
		}
	}

	private boolean injectWithSetter(Object target, Object dependency) {
		Method method = getMatchingSetter(target, dependency);
		if (method != null) {
			try {
				if (!method.isAccessible()) {
					method.setAccessible(true);
				}
				method.invoke(target, dependency);
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}

	private Method getMatchingSetter(Object target, Object dependency) {
		List<Method> methods = Accessor.methods(target.getClass());
		for (Method method : methods) {
			if (!method.getName().startsWith("set")) {
				continue;
			}
			if (method.getParameterTypes().length != 1) {
				continue;
			}
			Class<?> expected = Autobox
					.toPrimitive(method.getParameterTypes()[0]);
			Class<?> actual = Autobox.toPrimitive(dependency.getClass());
			if (!expected.isAssignableFrom(actual)) {
				continue;
			}
			return method;
		}
		return null;
	}

	private Field getMatchingField(Object target, Object dependency) {
		List<Field> fields = Accessor.fieldsAssignableFrom(dependency
				.getClass(), target.getClass());
		if (fields.size() > 1) {
			throw new RuntimeException("Multiple fields of matching type: "
					+ dependency.getClass().getName());
		}
		if (fields.size() == 0) {
			throw new RuntimeException("No field of matching type: "
					+ dependency.getClass().getName());
		}
		Field field = fields.get(0);
		return field;
	}

	private void inject(Object target, Object dependency, Field accessor) {
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
