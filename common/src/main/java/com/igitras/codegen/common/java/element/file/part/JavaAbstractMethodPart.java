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


import com.igitras.codegen.common.java.element.enums.IsAbstract;
import com.igitras.codegen.common.utils.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class JavaAbstractMethodPart extends AbstractJavaFilePart {

    private final IsAbstract isAbstract;
    private final String returnType;
    private final String methodName;
    private final JavaMethodParamsPart paramsPart = new JavaMethodParamsPart();
    private final JavaThrowsPart throwsPart = new JavaThrowsPart();
    private final JavaAnnotationsPart annotationsPart = new JavaAnnotationsPart();
    private String comment;

    public JavaAbstractMethodPart(IsAbstract isAbstract, String returnType, String methodName) {
        Assert.notNull(paramsPart, "Java Method Params Part must not be null.");
        Assert.notNull(throwsPart, "Java Throws Part must not be null");
        Assert.hasLength(methodName, "Method Name must have length.");
        Assert.hasLength(returnType, "Return Type must not be null.");

        if (isAbstract == null) {
            isAbstract = IsAbstract.DEFAULT;
        }
        this.isAbstract = isAbstract;
        this.returnType = returnType;
        this.methodName = methodName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public IsAbstract getIsAbstract() {
        return isAbstract;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public JavaMethodParamsPart getParamsPart() {
        return paramsPart;
    }

    public JavaThrowsPart getThrowsPart() {
        return throwsPart;
    }

    public JavaAnnotationsPart getAnnotationsPart() {
        return annotationsPart;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> imports = new HashSet<JavaImportPart>();
        imports.add(new JavaImportPart(returnType));
        imports.addAll(paramsPart.getImports());
        imports.addAll(throwsPart.getImports());
        imports.addAll(annotationsPart.getImports());
        return Collections.unmodifiableSet(imports);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaAbstractMethodPart)) {
            return false;
        }

        JavaAbstractMethodPart that = (JavaAbstractMethodPart) o;

        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) {
            return false;
        }
        if (paramsPart != null ? !paramsPart.equals(that.paramsPart) : that.paramsPart != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (paramsPart != null ? paramsPart.hashCode() : 0);
        return result;
    }
}
