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

package com.igitras.codegen.common.java.element.file;

import com.igitras.codegen.common.ProjectDirectory;
import com.igitras.codegen.common.java.element.JavaPackage;
import com.igitras.codegen.common.java.element.enums.IsAbstract;
import com.igitras.codegen.common.java.element.enums.VisitPrivilege;
import com.igitras.codegen.common.java.element.file.part.JavaAbstractMethodPart;
import com.igitras.codegen.common.java.element.file.part.JavaAbstractMethodsPart;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class JavaInterfaceFileTest {

    private JavaInterfaceFile interfaceFile;

    @Before
    public void setUp() throws Exception {

        String comment = "/** Test Interface */";
        VisitPrivilege visitPrivilege = VisitPrivilege.PUBLIC;
        String className = "TestInterface";

        JavaAbstractMethodsPart methodsPart = new JavaAbstractMethodsPart();
        JavaAbstractMethodPart methodPart = new JavaAbstractMethodPart(IsAbstract.DEFAULT, String.class.getName(),
                "testInterfaceMethods");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(IsAbstract.DEFAULT, String.class.getName(), "testInterfaceMethods1");
        methodsPart.addParts(methodPart);
        methodPart = new JavaAbstractMethodPart(null, String.class.getName(), "testInterfaceMethods2");
        methodsPart.addParts(methodPart);
        File baseDirectory = new File("/Volumes/BACKUP/Mac/SCM/CodeGen/common/target");
        ProjectDirectory main = new ProjectDirectory(baseDirectory, "main");
        interfaceFile = new JavaInterfaceFile(className, new JavaPackage(main, "org.personal.mason"));

    }

    @Test
    public void testBuild() throws Exception {
        System.out.println(interfaceFile.build());
    }

    @Test
    public void testAddField() throws Exception {

    }

    @Test
    public void testRemoveField() throws Exception {

    }

    @Test
    public void testAddAbstractMethod() throws Exception {

    }

    @Test
    public void testRemoveAbstractMethod() throws Exception {

    }

    @Test
    public void testAddConstructor() throws Exception {

    }

    @Test
    public void testRemoveConstructor() throws Exception {

    }

    @Test
    public void testAddMethod() throws Exception {

    }

    @Test
    public void testRemoveMethod() throws Exception {

    }
}