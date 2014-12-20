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

package com.igitras.codegen.axon;

import com.igitras.codegen.common.Directory;
import com.igitras.codegen.common.Project;
import com.igitras.codegen.common.java.element.JavaPackage;
import com.igitras.codegen.common.java.element.enums.IsAbstract;
import com.igitras.codegen.common.java.element.enums.IsFinal;
import com.igitras.codegen.common.java.element.enums.IsStatic;
import com.igitras.codegen.common.java.element.enums.VisitPrivilege;
import com.igitras.codegen.common.java.element.file.part.*;
import com.igitras.codegen.common.java.project.JavaProjectStructure;
import com.igitras.codegen.common.java.project.JavaSourceFolder;
import com.igitras.codegen.common.utils.Assert;
import com.igitras.codegen.common.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mason on 12/15/14.
 */
public abstract class BaseGen {

    private Project project;
    private JavaSourceFolder sourceFolder;
    protected JavaExtendsPart extendsPart = new JavaExtendsPart();
    protected JavaImplementsPart implementsPart = new JavaImplementsPart();
    protected JavaFieldsPart fieldsPart = new JavaFieldsPart();
    protected JavaAbstractMethodsPart abstractMethodsPart = new JavaAbstractMethodsPart();
    protected JavaMethodsPart methodsPart = new JavaMethodsPart();
    protected JavaConstructorsPart constructorsPart = new JavaConstructorsPart();
    protected String basePackage;
    protected String comment;
    protected VisitPrivilege visitPrivilege;
    protected IsFinal isFinal;
    protected IsAbstract isAbstract;

    protected BaseGen() {
    }

    public BaseGen withBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public BaseGen withFieldPart(JavaFieldPart javaFieldPart) {
        fieldsPart.addParts(javaFieldPart);
        return this;
    }

    public BaseGen withExtend(String extendClazz) {
        extendsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(extendClazz));
        return this;
    }

    public BaseGen withImplement(String implementInterface) {
        implementsPart.getCommaSplitList().addEntries(new JavaCommaSplitEntry(implementInterface));
        return this;
    }

    public BaseGen withAbstractMethod(JavaAbstractMethodPart abstractMethodPart) {
        abstractMethodsPart.addParts(abstractMethodPart);
        return this;
    }

    public BaseGen withMethod(JavaMethodPart methodPart) {
        methodsPart.addParts(methodPart);
        return this;
    }

    public BaseGen withConstructor(JavaConstructorPart constructorPart) {
        constructorsPart.addParts(constructorPart);
        return this;
    }

    public BaseGen withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public BaseGen withAbstract(IsAbstract isAbstract) {
        this.isAbstract = isAbstract;
        return this;
    }

    public BaseGen withFinal(IsFinal isFinal) {
        this.isFinal = isFinal;
        return this;
    }

    public BaseGen withVisitPrivilege(VisitPrivilege visitPrivilege) {
        this.visitPrivilege = visitPrivilege;
        return this;
    }

    public BaseGen withinProject(Project project) {
        this.project = project;
        this.basePackage = ((JavaProjectStructure) project.getProjectStructure()).getBasePackage();
        return this;
    }

    public BaseGen sourceFolder(JavaSourceFolder sourceFolder) {
        this.sourceFolder = sourceFolder;
        return this;
    }


    public abstract String getPackage();

    public abstract String getSourceRelatedFile();

    public abstract String getName();

    public abstract void generate();

    public JavaPackage javaPackage() {
        String sourceFolderName = sourceFolder.name();
        String packageName = getPackage();
        String concatPackageName = StringUtils.concat(sourceFolderName, packageName);
        Directory packageDirectory = project.getProjectStructure().getDirectory(sourceFolderName + concatPackageName);
        if (packageDirectory == null) {
            Directory parentDirectory = project.getProjectStructure().getDirectory(sourceFolderName);
            packageDirectory = new JavaPackage(parentDirectory, packageName);
            parentDirectory.addElement(packageDirectory);

            project.getProjectStructure().addDirectory(concatPackageName, packageDirectory);
        }
        return (JavaPackage) packageDirectory;
    }

    public void preGenerate() {
        Assert.notNull(project, "File to be generate must be in a project.");
        Assert.notNull(sourceFolder, "File must be in a given project folder.");
        Assert.hasLength(basePackage, "Base Package must have length.");

    }

    public void postGenerate() {

    }

    protected void writeGenerate(String content) {
        try {
            String folder = project.getProjectStructure().getDirectory(sourceFolder.name()) + getSourceRelatedFile();

            File outputFile = new File(folder);
            FileUtils.writeStringToFile(outputFile, content);
        } catch (IOException e) {
        }
    }

    protected void generateConstructorWithFields() {
        Iterator<JavaFieldPart> fieldPartIterator = fieldsPart.getParts();
        JavaMethodParamsPart methodParamsPart = new JavaMethodParamsPart();
        while (fieldPartIterator.hasNext()) {
            JavaFieldPart fieldPart = fieldPartIterator.next();
            if (fieldPart.getIsFinal() == IsFinal.FINAL && fieldPart.getIsStatic() == IsStatic.DEFAULT) {
                methodParamsPart.getCommaSplitList().addEntries(
                        new JavaCommaSplitEntry(fieldPart.getFieldType(), fieldPart.getFieldName()));
            }
        }

        constructorsPart.addParts(new JavaConstructorPart(VisitPrivilege.PUBLIC, getName(), methodParamsPart,
                                                          JavaMethodLogicalBuilder
                                                                  .setFieldLogicalBuilder(methodParamsPart).build()));
    }

    protected void generateGetMethodsWithFields() {
        Iterator<JavaFieldPart> fieldPartIterator = fieldsPart.getParts();
        while (fieldPartIterator.hasNext()) {
            JavaFieldPart fieldPart = fieldPartIterator.next();
            if (fieldPart.getIsFinal() == IsFinal.FINAL && fieldPart.getIsStatic() == IsStatic.DEFAULT) {
                methodsPart.addParts(new JavaMethodPart(VisitPrivilege.PUBLIC, IsStatic.DEFAULT, IsFinal.DEFAULT,
                                                        fieldPart.getFieldType(), fieldPart.getFieldName(),
                                                        JavaMethodLogicalBuilder.returnFieldLogicalBuilder(fieldPart)
                                                                                .build()));
            }
        }
    }
}
