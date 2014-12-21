package com.igitras.codegen.common.java.utils;

import com.igitras.codegen.common.java.element.JavaFile;
import com.igitras.codegen.common.java.project.JavaSourceFolder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

public class UtilsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testReflectResolveFile() throws Exception {

    }

    @Test
    public void testReflectResolveClass() throws Exception {

    }

    @Test
    public void testReflectResolveAnnotation() throws Exception {
        Utils.reflectResolveFile(FixMethodOrder.class.getName(), getDirectory(JavaSourceFolder.DUMY_FOLDER.name()));
    }

    @Test
    public void testReflectResolveInterface() throws Exception {
        JavaFile javaFile = Utils.reflectResolveFile(TestInterface2.class.getName(),
                                                     getDirectory(JavaSourceFolder.DUMY_FOLDER.name()));
        System.out.println(javaFile);
    }

    @Test
    public void testReflectResolveEnum() throws Exception {

    }
}

@Deprecated
interface TestInterface {

    public String abc = null;

    String abc1 = null;

    String abc2 = null;


    @Deprecated
    public void testAba();

    @Deprecated
    public void testAba1();

    @Deprecated
    public void testAb2();
}

@Deprecated
interface TestInterface2 extends TestInterface {

    public String abc = null;

    String abc1 = null;

    String abc2 = null;


    @Deprecated
    public void testAba();

    @Deprecated
    public void testAba1();

    @Deprecated
    public void testAb2();
}