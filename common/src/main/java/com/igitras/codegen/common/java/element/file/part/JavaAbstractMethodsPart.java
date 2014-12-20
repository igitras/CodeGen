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
public class JavaAbstractMethodsPart extends AbstractJavaFilePart implements JavaMultiplePart<JavaAbstractMethodPart> {
    private final List<JavaAbstractMethodPart> abstractMethodParts = new ArrayList<JavaAbstractMethodPart>();

    public JavaAbstractMethodsPart() {
        this(new ArrayList<JavaAbstractMethodPart>());
    }

    public JavaAbstractMethodsPart(List<JavaAbstractMethodPart> abstractMethodParts) {
        Assert.notNull(abstractMethodParts, "Java Abstract Methods Part must not be null.");
        addParts(abstractMethodParts.toArray(new JavaAbstractMethodPart[abstractMethodParts.size()]));
    }

    @Override
    public Iterator<JavaAbstractMethodPart> getParts() {
        return abstractMethodParts.iterator();
    }

    @Override
    public void addParts(JavaAbstractMethodPart... parts) {
        if (parts.length <= 0) {
            return;
        }

        for (JavaAbstractMethodPart part : parts) {
            if (!abstractMethodParts.contains(part)) {
                abstractMethodParts.add(part);
            }
        }
    }

    @Override
    public Class<JavaAbstractMethodPart> getElementType() {
        return JavaAbstractMethodPart.class;
    }

    public List<JavaAbstractMethodPart> getAbstractMethodParts() {
        return abstractMethodParts;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> imports = new HashSet<JavaImportPart>();
        for (JavaAbstractMethodPart abstractMethodPart : abstractMethodParts) {
            imports.addAll(abstractMethodPart.getImports());
        }
        return Collections.unmodifiableSet(imports);
    }
}
