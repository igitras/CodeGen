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

package com.igitras.codegen.common.java.element.file;


import com.igitras.codegen.common.java.element.JavaDirectory;
import com.igitras.codegen.common.java.element.JavaFile;
import com.igitras.codegen.common.java.element.JavaPackage;
import com.igitras.codegen.common.java.element.enums.IsAbstract;
import com.igitras.codegen.common.java.element.enums.IsFinal;
import com.igitras.codegen.common.java.element.enums.VisitPrivilege;
import com.igitras.codegen.common.java.element.file.part.*;
import com.igitras.codegen.common.utils.StringUtils;

import java.util.Set;

/**
 * Created by mason on 2014-12-01.
 */
public class JavaClassFile extends JavaFile implements JavaFileInterface {

    private final CopyrightPart copyrightPart;
    private final JavaPackagePart packagePart;
    private final JavaImportsPart importsPart;
    private final JavaExtendsPart extendsPart;
    private final JavaImplementsPart implementsPart;
    private final String className;
    private final JavaFieldsPart fieldsPart;
    private final JavaConstructorsPart constructorsPart;
    private final JavaAbstractMethodsPart abstractMethodsPart;
    private final JavaMethodsPart methodsPart;
    private final JavaAnnotationsPart annotationsPart;
    private String comment;
    private VisitPrivilege visitPrivilege;
    private IsFinal isFinal;
    private IsAbstract isAbstract;

    public JavaClassFile(String name, JavaDirectory directory) {
        super(name, directory);
        this.className = name;
        this.packagePart = new JavaPackagePart(StringUtils.getPackageName(name));
        this.copyrightPart = new CopyrightPart();
        this.importsPart = new JavaImportsPart();
        this.extendsPart = new JavaExtendsPart();
        this.implementsPart = new JavaImplementsPart();
        this.fieldsPart = new JavaFieldsPart();
        this.constructorsPart = new JavaConstructorsPart();
        this.abstractMethodsPart = new JavaAbstractMethodsPart();
        this.methodsPart = new JavaMethodsPart();
        this.annotationsPart = new JavaAnnotationsPart();
    }

    public CopyrightPart getCopyrightPart() {
        return copyrightPart;
    }

    public JavaPackagePart getPackagePart() {
        return packagePart;
    }

    public JavaExtendsPart getExtendsPart() {
        return extendsPart;
    }

    public JavaImplementsPart getImplementsPart() {
        return implementsPart;
    }

    public String getComment() {
        return comment;
    }

    public VisitPrivilege getVisitPrivilege() {
        return visitPrivilege;
    }

    public IsFinal getIsFinal() {
        return isFinal;
    }

    public IsAbstract getIsAbstract() {
        return isAbstract;
    }

    public String getClassName() {
        return className;
    }

    public JavaFieldsPart getFieldsPart() {
        return fieldsPart;
    }

    public JavaConstructorsPart getConstructorsPart() {
        return constructorsPart;
    }

    public JavaAbstractMethodsPart getAbstractMethodsPart() {
        return abstractMethodsPart;
    }

    public JavaMethodsPart getMethodsPart() {
        return methodsPart;
    }

    protected void collectImports() {
        collectJavaFilePartImports(copyrightPart.getImports());
        collectJavaFilePartImports(packagePart.getImports());
        collectJavaFilePartImports(importsPart.getImports());
        collectJavaFilePartImports(extendsPart.getImports());
        collectJavaFilePartImports(implementsPart.getImports());
        collectJavaFilePartImports(fieldsPart.getImports());
        collectJavaFilePartImports(constructorsPart.getImports());
        collectJavaFilePartImports(abstractMethodsPart.getImports());
        collectJavaFilePartImports(methodsPart.getImports());
        collectJavaFilePartImports(annotationsPart.getImports());
    }

    @Override
    public void withComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void withPrivilege(VisitPrivilege privilege) {
        this.visitPrivilege = privilege;
    }

    @Override
    public void withAbstract(IsAbstract isAbstract) {
        this.isAbstract = isAbstract;
    }

    @Override
    public void withFinal(IsFinal isFinal) {
        this.isFinal = isFinal;
    }

    protected void collectJavaFilePartImports(Set<JavaImportPart> imports) {
        if (imports.size() > 0) {
            this.importsPart.addParts(imports.toArray(new JavaImportPart[imports.size()]));
        }
    }

    @Override
    public void preBuild() {
        if (comment == null) {
            comment = "";
        }

        if (visitPrivilege == null) {
            visitPrivilege = VisitPrivilege.DEFAULT;
        }

        if (isAbstract == null) {
            isAbstract = IsAbstract.DEFAULT;
        }

        if (isFinal == null) {
            isFinal = IsFinal.DEFAULT;
        }

        if (isAbstract != IsAbstract.DEFAULT) {
            isFinal = IsFinal.DEFAULT;
        }
    }

    @Override
    public void postBuild() {

    }

    @Override
    public void addField(JavaFieldPart fieldPart) {
        fieldsPart.addParts(fieldPart);
    }

    @Override
    public void removeField(JavaFieldPart fieldPart) {
        fieldsPart.getFieldParts().remove(fieldPart);
    }

    @Override
    public void addAbstractMethod(JavaAbstractMethodPart abstractMethodPart) {
        if (isAbstract == IsAbstract.DEFAULT) {
            throw new UnsupportedOperationException("Cannot Add abstract class from a normal class.");
        }
        abstractMethodsPart.addParts(abstractMethodPart);
    }

    @Override
    public void removeAbstractMethod(JavaAbstractMethodPart abstractMethodPart) {
        abstractMethodsPart.getAbstractMethodParts().remove(abstractMethodPart);
    }

    @Override
    public void addConstructor(JavaConstructorPart constructorPart) {
        constructorsPart.addParts(constructorPart);
    }

    @Override
    public void removeConstructor(JavaConstructorPart constructorPart) {
        constructorsPart.getConstructorParts().remove(constructorPart);
    }

    @Override
    public void addMethod(JavaMethodPart methodPart) {
        methodsPart.addParts(methodPart);
    }

    @Override
    public void removeMethod(JavaMethodPart methodPart) {
        methodsPart.getMethodParts().remove(methodPart);
    }
}

