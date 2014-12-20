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
public class JavaMethodsPart extends AbstractJavaFilePart implements JavaMultiplePart<JavaMethodPart> {

    private final List<JavaMethodPart> methodParts = new ArrayList<JavaMethodPart>();

    public JavaMethodsPart() {
        this(new ArrayList<JavaMethodPart>());
    }

    public JavaMethodsPart(List<JavaMethodPart> methodParts) {
        Assert.notNull(methodParts, "Java Method Parts must not be null.");
        addParts(methodParts.toArray(new JavaMethodPart[methodParts.size()]));
    }

    public List<JavaMethodPart> getMethodParts() {
        return methodParts;
    }

    @Override
    public Iterator<JavaMethodPart> getParts() {
        return methodParts.iterator();
    }

    @Override
    public void addParts(JavaMethodPart... parts) {
        if (parts.length <= 0) {
            return;
        }
        for (JavaMethodPart part : parts) {
            if (!methodParts.contains(part)) {
                methodParts.add(part);
            }
        }
    }

    @Override
    public Class<JavaMethodPart> getElementType() {
        return JavaMethodPart.class;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> result = new HashSet<JavaImportPart>();
        for (JavaMethodPart methodPart : methodParts) {
            result.addAll(methodPart.getImports());
        }
        return Collections.unmodifiableSet(result);
    }
}
