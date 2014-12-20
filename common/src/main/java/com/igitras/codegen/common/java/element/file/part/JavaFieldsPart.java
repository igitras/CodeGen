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

import java.util.*;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class JavaFieldsPart extends AbstractJavaFilePart implements JavaMultiplePart<JavaFieldPart> {
    private final List<JavaFieldPart> fieldParts = new ArrayList<JavaFieldPart>();

    public JavaFieldsPart() {
        this(new ArrayList<JavaFieldPart>());
    }

    public JavaFieldsPart(List<JavaFieldPart> fieldParts) {
        Assert.notNull(fieldParts, "Java Field Parts must not be null.");
        addParts(fieldParts.toArray(new JavaFieldPart[fieldParts.size()]));
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> result = new HashSet<JavaImportPart>();
        for (JavaFieldPart fieldPart : fieldParts) {
            result.addAll(fieldPart.getImports());
        }
        return Collections.unmodifiableSet(result);
    }

    public List<JavaFieldPart> getFieldParts() {
        return fieldParts;
    }

    @Override
    public Iterator<JavaFieldPart> getParts() {
        return fieldParts.iterator();
    }

    @Override
    public void addParts(JavaFieldPart... parts) {
        if (parts.length <= 0) {
            return;
        }

        for (JavaFieldPart part : parts) {
            if (!fieldParts.contains(part)) {
                fieldParts.add(part);
            }
        }
    }

    @Override
    public Class<JavaFieldPart> getElementType() {
        return JavaFieldPart.class;
    }
}
