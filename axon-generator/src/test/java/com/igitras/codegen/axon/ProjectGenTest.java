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
import com.igitras.codegen.common.java.project.JavaProjectType;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ProjectGenTest {

    private ProjectGen projectGen;

    @Before
    public void setUp() throws Exception {
        File file = new File("/Volumes/BACKUP/Mac/SCM/CodeGen/axon-generator/target/TestProject/");

        if(file.exists()){
            FileUtils.deleteDirectory(file);
        }
        projectGen = ProjectGen.getInstance()
                .withName("TestProject")
                .withType(JavaProjectType.JAR)
                .withBasePackage("org.personal.mason")
                .withDescription("Test Project")
                .withVersion("1.0.1")
                .withBaseDirectory("/Volumes/BACKUP/Mac/SCM/CodeGen/axon-generator/target");

    }

    @Test
    public void testBuild() throws Exception {
        Project project = projectGen.build();
        project.generate();
    }
}