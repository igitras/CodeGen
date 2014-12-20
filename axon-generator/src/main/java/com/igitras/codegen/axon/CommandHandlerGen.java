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

import com.igitras.codegen.common.utils.StringUtils;

import java.io.File;

/**
 * Created by mason on 12/13/14.
 */
public class CommandHandlerGen extends BaseGen {

    private static final String SUFFIX = ".command.handler";
    private static final String CLASS_NAME_SUFFIX = "CommandHandler";

    private String commandHandlerName;

    private CommandHandlerGen() {
    }

    public static CommandHandlerGen getInstance() {
        return new CommandHandlerGen();
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
        return commandHandlerName + CLASS_NAME_SUFFIX;
    }

    @Override
    public void generate() {
        generateConstructorWithFields();
        generateConstructorWithFields();
        //        JavaClassFile classFile = new JavaClassFile(copyrightPart, new JavaPackagePart(basePackage + SUFFIX),
        //                extendsPart, implementsPart, comment, visitPrivilege, isFinal, isAbstract, commandHandlerName + CLASS_NAME_SUFFIX,
        //                fieldsPart, constructorsPart, abstractMethodsPart, methodsPart);
        //        writeGenerate(classFile.build());
    }
}
