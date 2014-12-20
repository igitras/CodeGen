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

import com.igitras.codegen.common.utils.Assert;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by m00290368 on 2014-12-05.
 */
public class JavaMethodLogicalBuilder {

    public static JavaMethodLogicalBuilder noneLogicalBuilder() {
        return new JavaMethodLogicalBuilder();
    }

    public static JavaMethodLogicalBuilder returnFieldLogicalBuilder(JavaFieldPart fieldPart) {
        return new DelegateJavaMethodLogicalBuild(new JavaMethodLogicalPart() {
            @Override
            public String build() {
                return "return " + fieldPart.getFieldName() + ";";
            }

            @Override
            public Set<JavaImportPart> getImports() {
                return fieldPart.getImports();
            }
        });
    }

    public static JavaMethodLogicalBuilder setFieldLogicalBuilder(JavaMethodParamsPart paramsPart) {
        return new DelegateJavaMethodLogicalBuild(new JavaMethodLogicalPart() {
            @Override
            public String build() {
                StringBuilder builder = new StringBuilder();
                Iterator<JavaCommaSplitEntry> entries = paramsPart.getCommaSplitList().getEntries();
                while (entries.hasNext()) {
                    JavaCommaSplitEntry entry = entries.next();
                    builder.append("this.").append(entry.getName()).append(" = ").append(entry.getName()).append(";")
                           .append(System.lineSeparator());
                }

                return builder.toString();
            }

            @Override
            public Set<JavaImportPart> getImports() {
                return paramsPart.getImports();
            }
        });
    }

    public JavaMethodLogicalPart build() {
        return new VoidJavaMethodLogicalPart();
    }

    private static class DelegateJavaMethodLogicalBuild extends JavaMethodLogicalBuilder {
        private final JavaMethodLogicalPart methodLogicalPart;

        public DelegateJavaMethodLogicalBuild(JavaMethodLogicalPart methodLogicalPart) {
            Assert.notNull(methodLogicalPart, "Java method logical part cannot be noll for delegate builder.");
            this.methodLogicalPart = methodLogicalPart;
        }

        @Override
        public JavaMethodLogicalPart build() {
            return methodLogicalPart;
        }
    }

    private class VoidJavaMethodLogicalPart extends JavaMethodLogicalPart {

        @Override
        public String build() {
            return "";
        }

        @Override
        public Set<JavaImportPart> getImports() {
            return Collections.emptySet();
        }
    }
}
