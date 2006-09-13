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
package org.laughingpanda.beaninject.target;

public class Child extends Parent {

    private String privateDeclaredField;

    protected String protectedDeclaredField;

    public String publicDeclaredField;

    String packagePrivateDeclaredField;

    private int privateDeclaredPrimitiveField;

    private String thePublicDeclaredProperty;

    private String theProtectedDeclaredProperty;

    private String thePackagePrivateDeclaredProperty;

    private String thePrivateDeclaredProperty;

    private int thePrivateDeclaredPrimitiveProperty;

    public void setPublicDeclaredProperty(String value) {
        thePublicDeclaredProperty = value;
    }

    protected void setProtectedDeclaredProperty(String value) {
        theProtectedDeclaredProperty = value;
    }

    void setPackagePrivateDeclaredProperty(String value) {
        thePackagePrivateDeclaredProperty = value;
    }

    private void setPrivateDeclaredProperty(String value) {
        thePrivateDeclaredProperty = value;
    }

    private void setPrivateDeclaredPrimitiveProperty(int value) {
        thePrivateDeclaredPrimitiveProperty = value;
    }
}
