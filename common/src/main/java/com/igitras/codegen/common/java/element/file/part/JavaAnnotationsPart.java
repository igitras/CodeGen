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

import java.util.*;

/**
 * Created by mason on 12/20/14.
 */
public class JavaAnnotationsPart extends AbstractJavaFilePart implements JavaMultiplePart<JavaAnnotationPart> {

    private final List<JavaAnnotationPart> annotationParts = new ArrayList<JavaAnnotationPart>();

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> imports = new HashSet<JavaImportPart>();
        for (JavaAnnotationPart annotationPart : annotationParts) {
            imports.addAll(annotationPart.getImports());
        }
        return Collections.unmodifiableSet(imports);
    }

    @Override
    public Iterator<JavaAnnotationPart> getParts() {
        return annotationParts.iterator();
    }

    @Override
    public void addParts(JavaAnnotationPart... parts) {
        if (parts.length <= 0) {
            return;
        }

        for (JavaAnnotationPart part : parts) {
            if (!annotationParts.contains(part)) {
                annotationParts.add(part);
            }
        }
    }

    public List<JavaAnnotationPart> getAnnotationParts() {
        return annotationParts;
    }

    @Override
    public Class<JavaAnnotationPart> getElementType() {
        return JavaAnnotationPart.class;
    }
}
