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

package com.igitras.codegen.common.java.element.file.part;

import com.igitras.codegen.common.utils.Assert;
import com.igitras.codegen.common.utils.StringUtils;

/**
 * Created by m00290368 on 2014-12-12.
 */
public class JavaCommaSplitEntry {

    private final String className;
    private final String name;

    public JavaCommaSplitEntry(String className) {
        this(className, null);
    }

    public JavaCommaSplitEntry(String className, String name) {
        Assert.hasLength(StringUtils.getClassName(className), "The class name must be a validate value.");
        this.className = className;
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaCommaSplitEntry)) {
            return false;
        }

        JavaCommaSplitEntry that = (JavaCommaSplitEntry) o;

        if (name != null) {
            if (that.name != null) {
                return name.equals(that.name);
            } else {
                return false;
            }
        } else {
            return className.equals(that.className);
        }
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(StringUtils.getClassName(className));
        if (name != null) {
            builder.append(" ");
            builder.append(name);
        }
        return builder.toString();
    }
}
