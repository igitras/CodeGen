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
import com.igitras.codegen.common.utils.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mason on 12/20/14.
 */
public class JavaAnnotationPart extends AbstractJavaFilePart {
    private final String annotationClass;
    private final JavaCommaSplitPart commaSplitPart;

    public JavaAnnotationPart(String annotationClass) {
        this(annotationClass, new JavaCommaSplitPart());
    }

    public JavaAnnotationPart(String annotationClass, JavaCommaSplitPart commaSplitPart) {
        Assert.notNull(commaSplitPart, "Java Comma split Part cannot be null.");
        Assert.notNull(annotationClass, "Annotation class cannot be null.");
        this.annotationClass = annotationClass;
        this.commaSplitPart = commaSplitPart;
    }

    @Override
    public String build() {
        StringBuilder builder = new StringBuilder("@");
        String className = StringUtils.getClassName(annotationClass);
        builder.append(className);
        if (commaSplitPart.hasEntry()) {
            builder.append("(");
            builder.append(commaSplitPart.build());
            builder.append(")");
        }
        return super.build();
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> imports = new HashSet<JavaImportPart>();
        imports.add(new JavaImportPart(annotationClass));
        imports.addAll(commaSplitPart.getImports());
        return Collections.unmodifiableSet(imports);
    }

    public String getAnnotationClass() {
        return annotationClass;
    }

    public JavaCommaSplitPart getCommaSplitPart() {
        return commaSplitPart;
    }
}
