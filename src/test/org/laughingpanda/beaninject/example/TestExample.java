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
package org.laughingpanda.beaninject.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;
import org.laughingpanda.beaninject.Inject;

/**
 * @author Lasse Koskela
 */
public class TestExample {

	private Target target;

	public class Target {
		private String field;

		public void setPropertyName(String value) {
			this.field = value;
		}

		public String getPropertyName() {
			return field;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestExample.class);
	}

	@Before
	public void setUp() {
		target = new Target();
		assertNull(target.getPropertyName());
	}

	@Test
	public void exampleOfInjectingWithSetter() throws Exception {
		Inject.property("propertyName").of(target).with("value");
		assertEquals("value", target.getPropertyName());
	}

	@Test
	public void exampleOfInjectingDirectlyToField() throws Exception {
		Inject.field("field").of(target).with("value");
		assertEquals("value", target.getPropertyName());
	}

	@Test
	public void exampleOfTypeBasedInjection() throws Exception {
		Inject.bean(target).with("value");
		assertEquals("value", target.getPropertyName());
	}
}
