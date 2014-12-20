/*
 * Copyright (c) 2014. igitras.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.igitras.codegen.common.java.element.file.part;

import com.igitras.codegen.common.java.element.enums.IsAbstract;
import org.junit.Test;

public class JavaAbstractMethodsPartTest {

    @Test
    public void testGetImports() throws Exception {

    }

    @Test
    public void testBuild() throws Exception {
        JavaAbstractMethodsPart methodsPart = new JavaAbstractMethodsPart();
        JavaAbstractMethodPart methodPart = new JavaAbstractMethodPart(IsAbstract.ABSTRACT, String.class.getName(),
                "testAbstractMethods");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(IsAbstract.ABSTRACT, String.class.getName(),
                "testAbstractMethods1");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(IsAbstract.ABSTRACT, String.class.getName(),
                "testAbstractMethods2");
        methodsPart.addParts(methodPart);
        System.out.println(methodsPart.build());
    }

    @Test
    public void testBuildForInterface() throws Exception {
        JavaAbstractMethodsPart methodsPart = new JavaAbstractMethodsPart();
        JavaAbstractMethodPart methodPart = new JavaAbstractMethodPart(IsAbstract.DEFAULT, String.class.getName(),
                "testAbstractMethods");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(IsAbstract.DEFAULT, String.class.getName(), "testAbstractMethods1");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(null, String.class.getName(), "testAbstractMethods2");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(null, String.class.getName(), "testAbstractMethods2");
        methodsPart.addParts(methodPart);
        System.out.println(methodsPart.build());
    }
}