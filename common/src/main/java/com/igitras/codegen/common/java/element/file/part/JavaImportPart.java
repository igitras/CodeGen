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
import com.igitras.codegen.common.utils.ReflectionUtils;
import com.igitras.codegen.common.utils.StringUtils;
import com.igitras.codegen.common.utils.TemplateUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Set;

/**
 * Created by mason on 2014-12-01.
 */
public class JavaImportPart extends AbstractJavaFilePart {

    private final String className;

    public JavaImportPart(String className) {
        Assert.hasLength(StringUtils.getClassName(className), "Must be an validate class name.");
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String build() {
        Iterable<Field> declaredFields = ReflectionUtils.fieldsOf(getClass());
        for (Field field : declaredFields) {
            Object fieldValue = ReflectionUtils.getFieldValue(field, this);
            String value;
            if (null == fieldValue) {
                value = "";
            } else {
                value = fieldValue.toString();
            }

            TemplateUtils.replaceProperty(field.getName(), value, templateBuilder);
        }
        return templateBuilder.toString();
    }

    @Override
    public Set<JavaImportPart> getImports() {
        return Collections.emptySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaImportPart)) {
            return false;
        }

        JavaImportPart that = (JavaImportPart) o;

        if (className != null ? !className.equals(that.className) : that.className != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return className != null ? className.hashCode() : 0;
    }
}
