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


import com.igitras.codegen.common.java.element.JavaPackage;
import com.igitras.codegen.common.java.element.file.JavaClassFile;
import com.igitras.codegen.common.java.element.file.part.JavaAbstractMethodPart;
import com.igitras.codegen.common.java.element.file.part.JavaConstructorPart;
import com.igitras.codegen.common.java.element.file.part.JavaFieldPart;
import com.igitras.codegen.common.java.element.file.part.JavaMethodPart;
import com.igitras.codegen.common.java.project.JavaSourceFolder;
import com.igitras.codegen.common.utils.StringUtils;

import java.io.File;
import java.util.Iterator;

/**
 * Created by mason on 12/13/14.
 */
public class CommandGen extends BaseGen {
    private static final String SUFFIX = ".command";
    private static final String CLASS_NAME_SUFFIX = "Command";

    private String commandName;

    private CommandGen() {
    }

    @Override
    public String getPackage() {
        return basePackage + SUFFIX;
    }

    @Override
    public String getSourceRelatedFile() {
        return StringUtils.packageToPath(getPackage()) + File.separator + getName();
    }

    @Override
    public String getName() {
        return commandName + CLASS_NAME_SUFFIX;
    }

    @Override
    public void generate() {
        generateConstructorWithFields();
        generateGetMethodsWithFields();

        JavaPackage javaPackage = javaPackage();

        JavaClassFile classFile = new JavaClassFile(getName(), javaPackage);

        classFile.withAbstract(isAbstract);
        classFile.withComment(comment);
        classFile.withFinal(isFinal);
        classFile.withPrivilege(visitPrivilege);

        Iterator<JavaFieldPart> fieldPartIterator = fieldsPart.getParts();
        while (fieldPartIterator.hasNext()) {
            classFile.addField(fieldPartIterator.next());
        }

        Iterator<JavaConstructorPart> constructorPartIterator = constructorsPart.getParts();
        while (constructorPartIterator.hasNext()) {
            classFile.addConstructor(constructorPartIterator.next());
        }

        Iterator<JavaAbstractMethodPart> abstractMethodPartIterator = abstractMethodsPart.getParts();
        while (abstractMethodPartIterator.hasNext()) {
            classFile.addAbstractMethod(abstractMethodPartIterator.next());
        }

        Iterator<JavaMethodPart> methodPartIterator = methodsPart.getParts();
        while (methodPartIterator.hasNext()) {
            classFile.addMethod(methodPartIterator.next());
        }

        javaPackage.addElement(classFile);
    }

    public static CommandGen getInstance() {
        CommandGen commandGen = new CommandGen();
        commandGen.sourceFolder(JavaSourceFolder.MAIN_JAVA_FOLDER);
        return commandGen;
    }

    public BaseGen withName(String commandName) {
        this.commandName = commandName;
        return this;
    }
}
