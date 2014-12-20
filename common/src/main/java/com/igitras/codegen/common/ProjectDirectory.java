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

package com.igitras.codegen.common;

import com.igitras.codegen.common.utils.Assert;
import com.igitras.codegen.common.utils.Utils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mason on 12/20/14.
 */
public class ProjectDirectory extends AbstractContainableElement implements Directory {
    private final Set<Element> children = new HashSet<Element>();
    private final File baseDirectory;

    public ProjectDirectory(File baseDirectory, String name) {
        super(name);
        Assert.notNull(baseDirectory, "Parent Containable must not be null.");
        baseDirectory = Utils.toCanonicalFile(baseDirectory);
        Assert.notNull(baseDirectory, "Project Base Path must not be null.");
        this.baseDirectory = baseDirectory;
        validatePath(baseDirectory);
        File projectFolder = Utils.getChildFile(baseDirectory, name);
        if (projectFolder.exists()) {
            throw new IllegalArgumentException("Project Already Exist");
        }
        projectFolder.mkdirs();
    }

    @Override
    public File getParent() {
        return baseDirectory;
    }

    private void validatePath(File parent) {

        if (!parent.isDirectory()) {
            throw new IllegalStateException("Parent must be a directory. ");
        }

        if (parent.isHidden()) {
            throw new IllegalStateException("Parent cannot be a hidden directory.");
        }

        if (!parent.canWrite()) {
            throw new IllegalStateException("Parent must be a writable directory.");
        }
    }
}
