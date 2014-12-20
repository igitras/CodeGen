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
import com.igitras.codegen.common.utils.Assert;
import com.igitras.codegen.common.utils.Utils;

import java.io.File;

/**
 * Created by mason on 12/19/14.
 */
public class JavaProjectConfiguration implements Configuration {

    private final String name;
    private final String location;
    private final String version;
    private final String description;

    public JavaProjectConfiguration(String name, String location, String version, String description) {
        Assert.hasLength(name, "Project name must have length.");
        Assert.hasLength(Utils.toCanonicalPath(new File(location)), "Project Location must have length.");
        Assert.hasLength(version, "Project version cannot be null.");
        this.name = name;
        this.location = location;
        this.version = version;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
