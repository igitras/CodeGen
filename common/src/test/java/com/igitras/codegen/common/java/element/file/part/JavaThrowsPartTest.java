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

import com.igitras.codegen.common.utils.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class JavaThrowsPartTest {

    @Test
    public void testBuild() throws Exception {
        JavaThrowsPart throwsPart = new JavaThrowsPart();
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(String.class.getName()));
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(String.class.getName()));
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(String.class.getName()));
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(Integer.class.getName()));

        List<String> src = new ArrayList<String>();
        src.add("String");
        src.add("Integer");
        assertEquals(throwsPart.build(), "throws " + StringUtils.join(", ", src));
    }

    @Test
    public void testGetImports() throws Exception {
        JavaThrowsPart throwsPart = new JavaThrowsPart();
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(String.class.getName()));
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(String.class.getName()));
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(String.class.getName()));
        throwsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(Integer.class.getName()));

        Set<JavaImportPart> imports = throwsPart.getImports();
        assertEquals(imports.size(), 2);
    }

}