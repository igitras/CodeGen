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

package com.igitras.codegen.common.java.project;

import com.igitras.codegen.common.AbstractStructure;
import com.igitras.codegen.common.Configuration;
import com.igitras.codegen.common.Directory;
import com.igitras.codegen.common.ProjectDirectory;
import com.igitras.codegen.common.java.element.JavaDirectory;
import com.igitras.codegen.common.java.element.JavaFile;
import com.igitras.codegen.common.java.element.JavaPackage;
import com.igitras.codegen.common.java.utils.Utils;
import com.igitras.codegen.common.utils.Assert;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.File;

/**
 * Created by mason on 12/18/14.
 */
public class JavaProjectStructure extends AbstractStructure<Directory, JavaFile> {

    private final String basePackage;
    private final Configuration projectConfiguration;

    public JavaProjectStructure(String basePackage, JavaProjectConfiguration projectConfiguration) {
        Assert.notNull(projectConfiguration, "Java project configuration cannot be null.");
        this.basePackage = basePackage;
        this.projectConfiguration = projectConfiguration;
    }

    public void initWithProjectType(JavaProjectType projectType) {
        Assert.notNull(projectType, "Java Project Type cannot be null.");
        ProjectDirectory projectDirectory =
                new ProjectDirectory(new File(projectConfiguration.getLocation()), projectConfiguration.getName());
        JavaDirectory srcDirectory = new JavaDirectory(projectDirectory, JavaSourceFolder.SRC_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.SRC_FOLDER.name(), srcDirectory);

        JavaDirectory mainDirectory = new JavaDirectory(srcDirectory, JavaSourceFolder.MAIN_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.MAIN_FOLDER.name(), mainDirectory);

        JavaDirectory mainJavaDirectory =
                new JavaDirectory(mainDirectory, JavaSourceFolder.MAIN_JAVA_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.MAIN_JAVA_FOLDER.name(), mainJavaDirectory);
        JavaDirectory mainResourcesDirectory =
                new JavaDirectory(mainDirectory, JavaSourceFolder.MAIN_RESOURCES_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.MAIN_RESOURCES_FOLDER.name(), mainResourcesDirectory);

        JavaDirectory testDirectory = new JavaDirectory(srcDirectory, JavaSourceFolder.TEST_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.TEST_FOLDER.name(), testDirectory);

        JavaDirectory testJavaDirectory =
                new JavaDirectory(testDirectory, JavaSourceFolder.TEST_JAVA_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.TEST_JAVA_FOLDER.name(), testJavaDirectory);
        JavaDirectory testResourcesDirectory =
                new JavaDirectory(testDirectory, JavaSourceFolder.TEST_RESOURCES_FOLDER.getFolderName());
        directoryNameMap.put(JavaSourceFolder.TEST_RESOURCES_FOLDER.name(), testResourcesDirectory);

        if (projectType == JavaProjectType.WAR) {
            JavaDirectory webDirectory = new JavaDirectory(srcDirectory, JavaSourceFolder.WEB_FOLDER.getFolderName());
            directoryNameMap.put(JavaSourceFolder.WEB_FOLDER.name(), webDirectory);
        }

        if (null == basePackage || basePackage.trim().isEmpty()) {
            return;
        }

        new JavaPackage(mainJavaDirectory, basePackage);
        new JavaPackage(testJavaDirectory, basePackage);

        directoryNameMap.put(JavaSourceFolder.DUMY_FOLDER.name(),
                             new JavaDirectory(srcDirectory, JavaSourceFolder.DUMY_FOLDER.getFolderName()));
    }


    public Configuration getProjectConfiguration() {
        return projectConfiguration;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public JavaFile resolveFile(String fileName) {
        JavaFile file = getFile(fileName);
        if (file == null) {
            file = Utils.reflectResolveFile(fileName, (JavaDirectory)getDirectory(JavaSourceFolder.DUMY_FOLDER.name()));
            if (file != null) {
                addFile(fileName, file);
            }
        }

        if (file == null) {
            throw new InvalidStateException(String.format("Unknown class with name %s.", fileName));
        }

        return file;
    }

}
