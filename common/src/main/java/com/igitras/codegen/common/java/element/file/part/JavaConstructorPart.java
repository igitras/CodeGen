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


import com.igitras.codegen.common.java.element.enums.VisitPrivilege;
import com.igitras.codegen.common.utils.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class JavaConstructorPart extends AbstractJavaFilePart {

    private final VisitPrivilege visitPrivilege;
    private final String constructorName;
    private final JavaMethodParamsPart methodParamsPart;
    private final JavaThrowsPart throwsPart = new JavaThrowsPart();
    private final JavaMethodLogicalPart logicalPart;
    private final JavaAnnotationsPart annotationsPart = new JavaAnnotationsPart();
    private String comment;

    public JavaConstructorPart(VisitPrivilege visitPrivilege, String constructorName) {
        this(visitPrivilege, constructorName, new JavaMethodParamsPart(),
             JavaMethodLogicalBuilder.noneLogicalBuilder().build());
    }

    public JavaConstructorPart(
            VisitPrivilege visitPrivilege, String constructorName, JavaMethodParamsPart methodParamsPart,
            JavaMethodLogicalPart logicalPart) {
        Assert.hasLength(constructorName, "Constructor Name must have length.");
        Assert.notNull(methodParamsPart, "Java Method Params Part must not be null.");
        Assert.notNull(throwsPart, "Java Throws Part must not be null.");
        Assert.notNull(logicalPart, "Java Method Logical Part must not be null.");

        if (visitPrivilege == null) {
            visitPrivilege = VisitPrivilege.DEFAULT;
        }
        this.visitPrivilege = visitPrivilege;
        this.constructorName = constructorName;
        this.logicalPart = logicalPart;
        this.methodParamsPart = methodParamsPart;
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

    public String getConstructorName() {
        return constructorName;
    }

    public JavaMethodParamsPart getMethodParamsPart() {
        return methodParamsPart;
    }

    public JavaThrowsPart getThrowsPart() {
        return throwsPart;
    }

    public JavaMethodLogicalPart getLogicalPart() {
        return logicalPart;
    }

    public JavaAnnotationsPart getAnnotationsPart() {
        return annotationsPart;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> imports = new HashSet<JavaImportPart>();
        imports.addAll(methodParamsPart.getImports());
        imports.addAll(throwsPart.getImports());
        imports.addAll(logicalPart.getImports());
        imports.addAll(annotationsPart.getImports());
        return Collections.unmodifiableSet(imports);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaConstructorPart)) {
            return false;
        }

        JavaConstructorPart that = (JavaConstructorPart) o;

        if (constructorName != null ? !constructorName.equals(that.constructorName) : that.constructorName != null) {
            return false;
        }
        if (methodParamsPart != null ? !methodParamsPart.equals(that.methodParamsPart) :
                that.methodParamsPart != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = constructorName != null ? constructorName.hashCode() : 0;
        result = 31 * result + (methodParamsPart != null ? methodParamsPart.hashCode() : 0);
        return result;
    }
}
