package org.laughingpanda.beaninject.impl;

import org.laughingpanda.beaninject.IVarargDependencyInjector;

public abstract class AbstractVarargDependencyInjector implements
		IVarargDependencyInjector {

	public abstract void with(Object... dependencies);

	public void with(Object dependency) {
		with(new Object[] { dependency });
	}
}
