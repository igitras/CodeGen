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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaCommaSplitPartTest {

    @Test
    public void testBuild() throws Exception {
        JavaCommaSplitPart splitClass = new JavaCommaSplitPart();
        splitClass.addEntries(new JavaCommaSplitEntry(String.class.getName()));
        splitClass.addEntries(new JavaCommaSplitEntry(Integer.class.getName()));
        splitClass.addEntries(new JavaCommaSplitEntry(Double.class.getName()));

        assertEquals(splitClass.build(), "String, Integer, Double");
    }

    @Test
    public void testGetImports() throws Exception {

    }
}