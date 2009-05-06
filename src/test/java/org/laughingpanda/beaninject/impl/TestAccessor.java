package org.laughingpanda.beaninject.impl;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.Test;
import org.laughingpanda.beaninject.target.annotation.MyPackagePrivateFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPrivateFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyProtectedFieldAnnotation;
import org.laughingpanda.beaninject.target.annotation.MyPublicFieldAnnotation;

public class TestAccessor {

    @SuppressWarnings("unused")
    @MyPrivateFieldAnnotation
    private int privateField;

    @MyPackagePrivateFieldAnnotation
    int packagePrivateField;

    @MyProtectedFieldAnnotation
    protected int protectedField;

    @MyPublicFieldAnnotation
    public int publicField;

    @Test
    public void findsPrivateFieldsWithAnnotation() throws Exception {
        assertAnnotationFound(MyPrivateFieldAnnotation.class,
                "privateField");
    }

    @Test
    public void findsPackagePrivateFieldsWithAnnotation()
            throws Exception {
        assertAnnotationFound(MyPackagePrivateFieldAnnotation.class,
                "packagePrivateField");
    }

    @Test
    public void findsProtectedFieldsWithAnnotation() throws Exception {
        assertAnnotationFound(MyProtectedFieldAnnotation.class,
                "protectedField");
    }

    @Test
    public void findsPublicFieldsWithAnnotation() throws Exception {
        assertAnnotationFound(MyPublicFieldAnnotation.class,
                "publicField");
    }

    private void assertAnnotationFound(
            Class<? extends Annotation> annotation, String fieldName) {
        Field field = Accessor.annotatedField(annotation, getClass());
        assertEquals(fieldName, field.getName());
    }
}
