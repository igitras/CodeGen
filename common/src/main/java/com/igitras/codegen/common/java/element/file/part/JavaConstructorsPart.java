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
public class JavaConstructorsPart extends AbstractJavaFilePart implements JavaMultiplePart<JavaConstructorPart> {

    private final List<JavaConstructorPart> constructorParts = new ArrayList<JavaConstructorPart>();

    public JavaConstructorsPart() {
        this(new ArrayList<JavaConstructorPart>());
    }

    public JavaConstructorsPart(List<JavaConstructorPart> constructorParts) {
        Assert.notNull(constructorParts, "Java Constructor Part must not be null.");
        addParts(constructorParts.toArray(new JavaConstructorPart[constructorParts.size()]));
    }

    @Override
    public Iterator<JavaConstructorPart> getParts() {
        return constructorParts.iterator();
    }

    @Override
    public void addParts(JavaConstructorPart... parts) {
        if (parts.length <= 0) {
            return;
        }
        for (JavaConstructorPart part : parts) {
            if (!constructorParts.contains(part)) {
                constructorParts.add(part);
            }
        }
    }

    @Override
    public Class<JavaConstructorPart> getElementType() {
        return JavaConstructorPart.class;
    }

    public List<JavaConstructorPart> getConstructorParts() {
        return constructorParts;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> imports = new HashSet<JavaImportPart>();
        for (JavaConstructorPart constructorPart : constructorParts) {
            imports.addAll(constructorPart.getImports());
        }
        return Collections.unmodifiableSet(imports);
    }
}
