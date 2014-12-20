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

import com.igitras.codegen.common.Configuration;
import com.igitras.codegen.common.Directory;
import com.igitras.codegen.common.Project;
import com.igitras.codegen.common.Structure;
import com.igitras.codegen.common.utils.Assert;

/**
 * Created by mason on 12/17/14.
 */
public class JavaModule implements JavaProject {
    private final Project parent;
    private final Structure projectStructure;
    private final Configuration configuration;

    public JavaModule(JavaProjectStructure projectStructure) {
        this(null, projectStructure);
    }

    public JavaModule(Project parent, final JavaProjectStructure projectStructure) {
        Assert.notNull(projectStructure, "Project Structure cannot be null.");
        this.parent = parent;
        this.projectStructure = projectStructure;
        this.configuration = projectStructure.getProjectConfiguration();
    }

    @Override
    public Structure getProjectStructure() {
        return projectStructure;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void generate() {
        //TODO:
        Directory srcDirectory = projectStructure.getDirectory(JavaSourceFolder.SRC_FOLDER.name());
        srcDirectory.construct();
    }

    @Override
    public Project getParent() {
        return parent;
    }
}
