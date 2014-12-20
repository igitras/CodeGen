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

import com.igitras.codegen.common.Module;
import com.igitras.codegen.common.Multiple;
import com.igitras.codegen.common.Project;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mason on 12/17/14.
 */
public class MultipleModuleProject extends JavaModule implements Multiple {

    private final Set<Module> children = new HashSet<Module>();

    public MultipleModuleProject(JavaProjectStructure projectStructure) {
        super(projectStructure);
    }

    public MultipleModuleProject(Project parent, JavaProjectStructure projectStructure) {
        super(parent, projectStructure);
    }

    @Override
    public Iterator<Module> getChildren() {
        return children.iterator();
    }

    @Override
    public void addChild(Module module) {
        if (!children.contains(module)) {
            children.add(module);
        }
    }

    @Override
    public void removeChild(Module module) {
        if (children.contains(module)) {
            children.remove(module);
        }
    }
}
