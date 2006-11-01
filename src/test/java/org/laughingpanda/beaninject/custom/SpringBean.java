package org.laughingpanda.beaninject.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SpringBean {

    String name();

}
