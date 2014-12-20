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


import com.igitras.codegen.common.java.element.enums.IsFinal;
import com.igitras.codegen.common.java.element.enums.IsStatic;
import com.igitras.codegen.common.java.element.enums.VisitPrivilege;
import com.igitras.codegen.common.utils.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mason on 2014-12-01.
 */
public class JavaFieldPart extends AbstractJavaFilePart {
    private final VisitPrivilege visitPrivilege;
    private final IsStatic isStatic;
    private final IsFinal isFinal;
    private final String fieldType;
    private final String fieldName;
    private final JavaInitialPart initialPart;
    private final JavaAnnotationsPart annotationsPart = new JavaAnnotationsPart();
    private String comment;

    public JavaFieldPart(
            VisitPrivilege visitPrivilege, IsStatic isStatic, IsFinal isFinal, String fieldType, String fieldName,
            JavaInitialPart initialPart) {
        Assert.notNull(initialPart, "Java initial Part must not be null.");
        Assert.hasLength(fieldType, "Java Field Type must have length.");
        Assert.hasLength(fieldName, "Java Field Name must have length.");

        if (visitPrivilege == null) {
            visitPrivilege = VisitPrivilege.DEFAULT;
        }

        if (isStatic == null) {
            isStatic = IsStatic.DEFAULT;
        }

        if (isFinal == null) {
            isFinal = IsFinal.DEFAULT;
        }

        this.visitPrivilege = visitPrivilege;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
        this.initialPart = initialPart;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public VisitPrivilege getVisitPrivilege() {
        return visitPrivilege;
    }

    public IsStatic getIsStatic() {
        return isStatic;
    }

    public IsFinal getIsFinal() {
        return isFinal;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public JavaInitialPart getInitialPart() {
        return initialPart;
    }

    public JavaAnnotationsPart getAnnotationsPart() {
        return annotationsPart;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> result = new HashSet<JavaImportPart>();
        result.add(new JavaImportPart(fieldType));
        result.addAll(initialPart.getImports());
        result.addAll(annotationsPart.getImports());
        return Collections.unmodifiableSet(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaFieldPart)) {
            return false;
        }

        JavaFieldPart that = (JavaFieldPart) o;

        if (fieldName != null ? !fieldName.equals(that.fieldName) : that.fieldName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return fieldName != null ? fieldName.hashCode() : 0;
    }
}

