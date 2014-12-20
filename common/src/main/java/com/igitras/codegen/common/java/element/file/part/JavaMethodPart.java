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
public class JavaMethodPart extends AbstractJavaFilePart {

    private final VisitPrivilege visitPrivilege;
    private final IsStatic isStatic;
    private final IsFinal isFinal;
    private final String returnType;
    private final String methodName;
    private final JavaMethodParamsPart methodParamsPart = new JavaMethodParamsPart();
    private final JavaThrowsPart throwsPart = new JavaThrowsPart();
    private final JavaMethodLogicalPart methodLogicalPart;
    private final JavaAnnotationsPart annotationsPart = new JavaAnnotationsPart();
    private String comment;

    public JavaMethodPart(
            VisitPrivilege visitPrivilege, IsStatic isStatic, IsFinal isFinal, String returnType, String methodName,
            JavaMethodLogicalPart methodLogicalPart) {

        Assert.notNull(methodParamsPart, "Java Method Params Part must not be null.");
        Assert.notNull(throwsPart, "Java Method Params Part must not be null.");
        Assert.notNull(methodLogicalPart, "Java Method Params Part must not be null.");

        Assert.hasLength(returnType, "Java Throws Part must have length.");
        Assert.hasLength(methodName, "Java Field Name must have length.");

        this.visitPrivilege = visitPrivilege;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.returnType = returnType;
        this.methodName = methodName;
        this.methodLogicalPart = methodLogicalPart;
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

    public String getReturnType() {
        return returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public JavaMethodParamsPart getMethodParamsPart() {
        return methodParamsPart;
    }

    public JavaThrowsPart getThrowsPart() {
        return throwsPart;
    }

    public JavaMethodLogicalPart getMethodLogicalPart() {
        return methodLogicalPart;
    }

    public JavaAnnotationsPart getAnnotationsPart() {
        return annotationsPart;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> result = new HashSet<JavaImportPart>();

        if (!"void".equals(returnType)) {
            result.add(new JavaImportPart(returnType));
        }

        result.addAll(methodParamsPart.getImports());
        result.addAll(throwsPart.getImports());
        result.addAll(methodLogicalPart.getImports());
        result.addAll(annotationsPart.getImports());
        return Collections.unmodifiableSet(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaMethodPart)) {
            return false;
        }

        JavaMethodPart that = (JavaMethodPart) o;

        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) {
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
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (methodParamsPart != null ? methodParamsPart.hashCode() : 0);
        return result;
    }
}

