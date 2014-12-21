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
public class JavaEnumFile extends JavaFile implements JavaFileInterface {
    private final CopyrightPart copyrightPart;
    private final JavaPackagePart packagePart;
    private final JavaImportsPart importsPart;
    private final String className;
    private final JavaEnumElementsPart enumElementsPart;
    private final JavaFieldsPart fieldsPart;
    private final JavaConstructorsPart constructorsPart;
    private final JavaMethodsPart methodsPart;
    private String comment;
    private VisitPrivilege visitPrivilege;

    public JavaEnumFile(String name, JavaDirectory directory) {
        super(name, directory);
        this.className = name;
        this.packagePart = new JavaPackagePart(StringUtils.getPackageName(name));
        this.copyrightPart = new CopyrightPart();
        this.importsPart = new JavaImportsPart();
        this.fieldsPart = new JavaFieldsPart();
        this.constructorsPart = new JavaConstructorsPart();
        this.methodsPart = new JavaMethodsPart();
        this.enumElementsPart = new JavaEnumElementsPart();
    }

    public CopyrightPart getCopyrightPart() {
        return copyrightPart;
    }

    public JavaPackagePart getPackagePart() {
        return packagePart;
    }

    public JavaImportsPart getImportsPart() {
        return importsPart;
    }

    public String getComment() {
        return comment;
    }

    public VisitPrivilege getVisitPrivilege() {
        return visitPrivilege;
    }

    public String getClassName() {
        return className;
    }

    public JavaEnumElementsPart getEnumElementsPart() {
        return enumElementsPart;
    }

    public JavaFieldsPart getFieldsPart() {
        return fieldsPart;
    }

    public JavaConstructorsPart getConstructorsPart() {
        return constructorsPart;
    }

    public JavaMethodsPart getMethodsPart() {
        return methodsPart;
    }

    @Override
    protected void collectImports() {
        collectJavaFilePartImports(copyrightPart.getImports());
        collectJavaFilePartImports(packagePart.getImports());
        collectJavaFilePartImports(importsPart.getImports());
        collectJavaFilePartImports(enumElementsPart.getImports());
        collectJavaFilePartImports(fieldsPart.getImports());
        collectJavaFilePartImports(constructorsPart.getImports());
        collectJavaFilePartImports(methodsPart.getImports());
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
        throw new UnsupportedOperationException("Cannot set enum class to abstract.");
    }

    @Override
    public void withFinal(IsFinal isFinal) {
        throw new UnsupportedOperationException("Cannot set enum class to final.");
    }

    @Override
    public void preBuild() {
        if (comment == null) {
            comment = "";
        }

        if (visitPrivilege == null) {
            visitPrivilege = VisitPrivilege.DEFAULT;
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
        throw new UnsupportedOperationException("Not Support to add an abstract method to enum class.");
    }

    @Override
    public void removeAbstractMethod(JavaAbstractMethodPart abstractMethodPart) {
        throw new UnsupportedOperationException("Not Support to remove an abstract method from enum class.");
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

    protected void collectJavaFilePartImports(Set<JavaImportPart> imports) {
        if (imports.size() > 0) {
            this.importsPart.addParts(imports.toArray(new JavaImportPart[imports.size()]));
        }
    }
}

