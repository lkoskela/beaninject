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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Koskela
 */
public class Autobox {

	private static Map<Class<?>, Class<?>> PRIMITIVES = new HashMap<Class<?>, Class<?>>();
	static {
		PRIMITIVES.put(Byte.class, byte.class);
		PRIMITIVES.put(Short.class, short.class);
		PRIMITIVES.put(Integer.class, int.class);
		PRIMITIVES.put(Long.class, long.class);
		PRIMITIVES.put(Float.class, float.class);
		PRIMITIVES.put(Double.class, double.class);
	}

	public static Class<?> toPrimitive(Class<?> type) {
		Class<?> primitiveType = PRIMITIVES.get(type);
		return primitiveType != null ? primitiveType : type;
	}

}
