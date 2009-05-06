package org.laughingpanda.beaninject.target.annotation;

public class AnnotatedClass {

    @MyPrivateFieldAnnotation
    @SuppressWarnings("unused")
    private String annotatedPrivateField;

    @MyProtectedFieldAnnotation
    protected String annotatedProtectedField;

    @MyPublicFieldAnnotation
    public String annotatedPublicField;

    @MyPackagePrivateFieldAnnotation
    String annotatedPackagePrivateField;

    public String propertyWithPublicSetter;

    @SuppressWarnings("unused")
    private String propertyWithProtectedSetter;

    @SuppressWarnings("unused")
    private String propertyWithPrivateSetter;

    @SuppressWarnings("unused")
    private String propertyWithPackagePrivateSetter;

    @MyPublicSetterAnnotation
    public void annotatedPublicSetter(String value) {
        this.propertyWithPublicSetter = value;
    }

    @MyProtectedSetterAnnotation
    protected void annotatedProtectedSetter(String value) {
        this.propertyWithProtectedSetter = value;
    }

    @SuppressWarnings("unused")
    @MyPrivateSetterAnnotation
    private void annotatedPrivateSetter(String value) {
        this.propertyWithPrivateSetter = value;
    }

    @SuppressWarnings("unused")
    @MyPackagePrivateSetterAnnotation
    private void annotatedPackagePrivateSetter(String value) {
        this.propertyWithPackagePrivateSetter = value;
    }
}
