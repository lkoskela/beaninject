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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import org.laughingpanda.beaninject.impl.Accessor;
import org.laughingpanda.beaninject.target.TypeClassA;
import org.laughingpanda.beaninject.target.TypeClassB;

/**
 * @author Lasse Koskela
 */
public class TestTypeInjectionWithMultipleValues {

	private Object target = new Object() {
		@SuppressWarnings("unused")
		public TypeClassA fieldA;
		@SuppressWarnings("unused")
		public TypeClassB fieldB;
	};

	@Test
	public void multipleObjectsToInjectByType() throws Exception {
		TypeClassA valueForA = new TypeClassA();
		TypeClassB valueForB = new TypeClassB();
		Inject.bean(target).with(valueForA, valueForB);
		assertEquals(readField("fieldA", target), valueForA);
		assertEquals(readField("fieldB", target), valueForB);
	}

	@Test
	public void multipleObjectsToInjectByTypeWhenSomeFail() throws Exception {
		try {
			Inject.bean(target).with(new TypeClassA(), new String());
			fail("Type-based injection should fail if injection of any of the provided values fails.");
		} catch (Exception expected) {
		}
		assertNull(readField("fieldA", target));
		assertNull(readField("fieldB", target));
	}

	private Object readField(String name, Object target) throws Exception {
		return Accessor.field(name, target.getClass()).get(target);
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestTypeInjectionWithMultipleValues.class);
	}
}
