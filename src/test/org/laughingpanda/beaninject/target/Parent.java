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

public class Parent {
    private String privateInheritedField;

    protected String protectedInheritedField;

    public String publicInheritedField;

    String packagePrivateInheritedField;

    private int privateInheritedPrimitiveField;

    private String thePublicInheritedProperty;

    private String theProtectedInheritedProperty;

    private String thePackagePrivateInheritedProperty;

    private String thePrivateInheritedProperty;

    private int thePrivateInheritedPrimitiveProperty;

    public void setPublicInheritedProperty(String value) {
        thePublicInheritedProperty = value;
    }

    protected void setProtectedInheritedProperty(String value) {
        theProtectedInheritedProperty = value;
    }

    void setPackagePrivateInheritedProperty(String value) {
        thePackagePrivateInheritedProperty = value;
    }

    private void setPrivateInheritedProperty(String value) {
        thePrivateInheritedProperty = value;
    }

    private void setPrivateInheritedPrimitiveProperty(int value) {
        thePrivateInheritedPrimitiveProperty = value;
    }

}
