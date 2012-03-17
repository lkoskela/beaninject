# BeanInject

BeanInject is a utility for simple injection of dependencies to "beans", that is, plain old Java objects.

It's not a dependency injection framework. It's an injection _utility_. You figure out what to inject and where, and use BeanInject to do the dirty work of manipulating the target object with Java's Reflection API. 

# How to install it?

Assuming you're using Maven, just add the following dependency block into your POM file:

    <dependency>
        <groupId>beaninject</groupId>
        <artifactId>beaninject</artifactId>
        <version>0.6</version>
    </dependency>

# How to use it?

The core of BeanInject is a class named _Inject_. Below is an example of its usage.

Consider an instance of the following class being the "target" of injection:

    public class Target {
        private String field;
        
        public void setPropertyName(String value) {
            this.field = value;
        }
        
        public String getPropertyName() {
            return field;
        }
    }

Now, for an instance of the above _Target_ class, we can use BeanInject to inject the value "value" to the field "field" either directly or using the appropriate setter method.

## Property-based injection

This is what the API call would look like for injecting through the property named "propertyName" (i.e. using a setter method "setPropertyName"):

    Inject.property("propertyName").of(target).with("value");

## Field-based injection

This is what the API call would look like for injecting directly to the field:

    Inject.field("field").of(target).with("value");

## Type-based injection

A third, perhaps less common method of injection is what we call type-based injection. With type-based injection, BeanInject will try to figure out a property or field to which the given object (value) should be injected based on its type (class). For the above _Target_ class with a property of type _java.lang.String_, we might use the type-based injection as follows:

    Inject.bean(target).with("value");

For the rather common situation where a setter method stores the given object into a field of the same type, the _Inject_ class will always invoke the setter method, thus not bypassing any processing or validation the setter might implement.

## Using a custom injection strategy

If none of the built-in injection strategies do it for you, there's the option of implementing your own injection strategy and pass it to the _Inject_ class as follows:

    Inject.with(new CustomInjectionStrategy()).bean(target);

The custom strategy class needs to implement the _IInjectionStrategy_ interface:

    public interface IInjectionStrategy {
       void inject(Object target);
    }

# Browse the source, Luke

For a more thorough understanding of how BeanInject works, take a look at its unit tests at https://github.com/lkoskela/beaninject/tree/master/src/test/java/org/laughingpanda/beaninject

# License

Copyright © 2007 original author or authors.

BeanInject is Licensed under the Apache License, Version 2.0. Please refer to the URL http://www.apache.org/licenses/LICENSE-2.0 for details.