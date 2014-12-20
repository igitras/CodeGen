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

package com.igitras.codegen.common.utils;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ReformatterTest {

    String source;
    @Before
    public void setUp() throws Exception {
        source = "  private  final String fields1 = new String (\"Test dfsd  sdf!\");";
        source += Reformatter.LINE_SEPARATOR;
        source += "  private  final String fields2 = new String (\"Test dfsd  sdffsadf!\");";
        source += Reformatter.LINE_SEPARATOR;
        source += "private  Map < String,  Object> map = new HashMap<String, Object > ();";
        source += Reformatter.LINE_SEPARATOR;
        source += "if( a > 4) { }";
    }

    @Test
    public void testFixup() throws Exception {
        String s = FileUtils.readFileToString(new File("/Volumes/BACKUP/Mac/SCM/CodeGen/axon-generator/target/TestProject/src/main/java/org/personal/mason/command/HelloCommand.java"));
        System.out.println(Reformatter.fixup(s));
    }

    @Test
    public void testRemoveTrailingSpace() throws Exception {
        String result = Reformatter.removeTrailingSpace(source);
        System.out.println(result);
    }

    @Test
    public void testCompressBlankLines() throws Exception {
        source = "fsdfsf"+Reformatter.LINE_SEPARATOR;
        source += "fsdfsf"+Reformatter.LINE_SEPARATOR;
        source += Reformatter.LINE_SEPARATOR;
        source += Reformatter.LINE_SEPARATOR;
        source += Reformatter.LINE_SEPARATOR;
        source += "fsdfsf"+Reformatter.LINE_SEPARATOR;
        System.out.println(Reformatter.compressBlankLines(source));
    }

    @Test
    public void testCompressSpace() throws Exception {
        String result = Reformatter.removeTrailingSpace(source);
        result = Reformatter.compressSpace(result);

        System.out.println(result);

        String s = FileUtils.readFileToString(new File("/Volumes/BACKUP/Mac/SCM/CodeGen/axon-generator/target/TestProject/src/main/java/org/personal/mason/command/HelloCommand.java"));
        result = Reformatter.compressSpace(s);
        result = Reformatter.removeTrailingSpace(result);
        result = Reformatter.addLineStartBlanks(result);
        System.out.println(result);
    }
}