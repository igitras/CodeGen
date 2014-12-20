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


import com.igitras.codegen.common.Project;
import com.igitras.codegen.common.java.project.*;
import com.igitras.codegen.common.utils.Assert;

/**
 * Created by mason on 12/13/14.
 */
public class ProjectGen {

    private String name;
    private String description;
    private JavaProjectType type;
    private String baseDirectory;
    private String basePackage;
    private String version;

    private ProjectGen() {
    }

    public static ProjectGen getInstance() {
        return new ProjectGen();
    }

    public ProjectGen withType(JavaProjectType type) {
        this.type = type;
        return this;
    }

    public ProjectGen withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectGen withVersion(String version) {
        this.version = version;
        return this;
    }

    public ProjectGen withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectGen withBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
        return this;
    }

    public ProjectGen withBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    protected Project build() {
        Assert.notNull(this.type, "Project Type must not be null.");
        Assert.notNull(this.name, "Project Name must not be null.");
        Assert.notNull(this.version, "Project Version cannot be null.");
        Assert.notNull(this.baseDirectory, "Project Base Directory must not be null.");
        Assert.notNull(this.basePackage, "Project Base Package must not be null.");

        return buildJavaProject();
    }

    private Project buildJavaProject() {
        JavaProjectConfiguration configuration =
                new JavaProjectConfiguration(this.name, this.baseDirectory, this.version, this.description);
        JavaProjectStructure structure = new JavaProjectStructure(this.basePackage, configuration);
        structure.initWithProjectType(this.type);

        if (this.type == JavaProjectType.POM) {
            return new MultipleModuleProject(structure);
        }
        return new JavaModule(structure);
    }
}
