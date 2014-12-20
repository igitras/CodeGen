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

package com.igitras.codegen.common.java.element;

import com.igitras.codegen.common.AbstractContainableElement;
import com.igitras.codegen.common.Containable;
import com.igitras.codegen.common.Directory;
import com.igitras.codegen.common.utils.Assert;

import java.io.File;

/**
 * Created by mason on 12/17/14.
 */
public class JavaDirectory extends AbstractContainableElement implements JavaElement, Directory {

    private final Containable parent;

    public JavaDirectory(Containable parent, String name) {
        super(name);
        Assert.notNull(parent, "Parent Containable must not be null.");
        this.parent = parent;
        parent.addElement(this);
    }

    @Override
    public File getParent() {
        return parent.getDirectory();
    }

}
