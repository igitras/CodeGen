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

import com.igitras.codegen.common.Containable;
import com.igitras.codegen.common.utils.StringUtils;

/**
 * Created by mason on 12/17/14.
 */
public class JavaPackage extends JavaDirectory {

    private final String packageName;

    public JavaPackage(Containable parent, String packageName) {
        super(parent, StringUtils.packageToPath(packageName));
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
