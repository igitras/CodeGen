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
public class JavaImportsPart extends AbstractJavaFilePart implements JavaMultiplePart<JavaImportPart> {

    private final List<JavaImportPart> importParts;

    public JavaImportsPart() {
        this(new ArrayList<JavaImportPart>());
    }

    public JavaImportsPart(List<JavaImportPart> importParts) {
        Assert.notNull(importParts, "Import Part must not be null.");
        this.importParts = importParts;
    }

    public List<JavaImportPart> getImportParts() {
        return importParts;
    }

    @Override
    public Iterator<JavaImportPart> getParts() {
        return importParts.iterator();
    }

    @Override
    public void addParts(JavaImportPart... parts) {
        if (parts.length <= 0) {
            return;
        }

        for (JavaImportPart part : parts) {
            if (!importParts.contains(part)) {
                importParts.add(part);
            }
        }
    }

    @Override
    public Class<JavaImportPart> getElementType() {
        return JavaImportPart.class;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        return Collections.emptySet();
    }
}
