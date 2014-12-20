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

import java.util.Iterator;

/**
 * Created by mason on 12/17/14.
 */
public abstract class AbstractElement implements Element {

    private final String name;

    public AbstractElement(String name) {
        Assert.hasLength(name, "Element name must have length.");
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void construct() {
        if (Containable.class.isAssignableFrom(getClass())) {
            Containable containable = (Containable) this;
            Utils.createDirectory(containable.getDirectory());
            Iterator<Element> elements = containable.getElements();
            while (elements.hasNext()) {
                elements.next().construct();
            }
        } else if (File.class.isAssignableFrom(getClass())) {
            File file = (File) this;
            String content = file.build();
            Utils.writeFileInDirectory(content, file.getName() + file.getSuffix(),
                                       file.getContainable().getDirectory());
        }
    }
}
