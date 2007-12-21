package org.laughingpanda.beaninject.target.annotation;


public class AnnotatedClass {

    @MyPrivateFieldAnnotation
    private String annotatedPrivateField;

    @MyProtectedFieldAnnotation
    protected String annotatedProtectedField;

    @MyPublicFieldAnnotation
    public String annotatedPublicField;

    @MyPackagePrivateFieldAnnotation
    String annotatedPackagePrivateField;

    public String propertyWithPublicSetter;

    private String propertyWithProtectedSetter;

    private String propertyWithPrivateSetter;

    private String propertyWithPackagePrivateSetter;

    @MyPublicSetterAnnotation
    public void annotatedPublicSetter(String value) {
        this.propertyWithPublicSetter = value;
    }

    @MyProtectedSetterAnnotation
    protected void annotatedProtectedSetter(String value) {
        this.propertyWithProtectedSetter = value;
    }

    @MyPrivateSetterAnnotation
    private void annotatedPrivateSetter(String value) {
        this.propertyWithPrivateSetter = value;
    }

    @MyPackagePrivateSetterAnnotation
    private void annotatedPackagePrivateSetter(String value) {
        this.propertyWithPackagePrivateSetter = value;
    }
}
