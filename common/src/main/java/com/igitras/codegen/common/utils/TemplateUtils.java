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

package com.igitras.codegen.common.utils;

import com.igitras.codegen.common.java.element.file.part.AbstractJavaFilePart;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by mason on 12/17/14.
 */
public class TemplateUtils {

    public static void processFields(Object object, StringBuilder templateBuilder) {
        Iterable<Field> declaredFields = ReflectionUtils.fieldsOf(object.getClass());

        for (Field field : declaredFields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Class<?> type = field.getType();
            if (type.isPrimitive()) {
                type = ReflectionUtils.resolvePrimitiveWrapperType(type);
            }

            if (type.isEnum()) {
                Object fieldValue = ReflectionUtils.getFieldValue(field, object);
                Assert.notNull(fieldValue, "Enum value must not be null.");
                String value = fieldValue.toString();
                if ("DEFAULT".equalsIgnoreCase(value)) {
                    value = "";
                } else {
                    value = value.toLowerCase();
                }

                replaceProperty(field.getName(), value, templateBuilder);
                continue;
            }

            if (AbstractJavaFilePart.class.isAssignableFrom(type)) {
                AbstractJavaFilePart javaFilePart = (AbstractJavaFilePart) ReflectionUtils.getFieldValue(field, object);
                String value = javaFilePart.build();
                replaceJavaFilePartProperty(type.getSimpleName(), value, templateBuilder);
                continue;
            }

            if (ReflectionUtils.isInPrimitiveWrapperType(type)) {
                Object fieldValue = ReflectionUtils.getFieldValue(field, object);
                String value;
                if (null == fieldValue) {
                    value = "";
                } else {
                    value = fieldValue.toString();
                }
                replaceProperty(field.getName(), value, templateBuilder);
                continue;
            }

            if (Serializable.class.isAssignableFrom(type)) {
                Object fieldValue = ReflectionUtils.getFieldValue(field, object);
                String value;
                if (null == fieldValue) {
                    value = "";
                } else {
                    value = fieldValue.toString();
                }

                if (field.getType() == String.class) {
                    value = StringUtils.getClassName(value);
                }

                replaceProperty(field.getName(), value, templateBuilder);
                continue;
            }
        }
    }

    public static void replaceProperty(String fieldName, String value, StringBuilder templateBuilder) {
        String keyword = String.format("##%s", fieldName);
        replace(value, keyword, templateBuilder);
    }

    public static void replaceJavaFilePartProperty(String typeName, String replacement, StringBuilder templateBuilder) {
        String keyword = String.format("#%s#", typeName);
        replace(replacement, keyword, templateBuilder);
    }

    public static void replaceMultipleJavaFilePartProperty(
            String typeName, String replacement, StringBuilder templateBuilder) {
        String keyword =
                "##Each##" + System.lineSeparator() + "#" + typeName + "#" + System.lineSeparator() + "##Each##";
        replace(replacement, keyword, templateBuilder);
    }

    /**
     * Replace all the keyword in the templateBuilder with replacement.
     *
     * @param replacement
     * @param keyword
     */
    private static void replace(String replacement, String keyword, StringBuilder templateBuilder) {
        int keywordStartIndex;
        if (replacement == null) {
            replacement = "";
        }

        while ((keywordStartIndex = templateBuilder.indexOf(keyword)) >= 0) {
            int keywordEndIndex = keywordStartIndex + keyword.length();
            if (keywordEndIndex <= keywordStartIndex) {
                break;
            }
            templateBuilder.replace(keywordStartIndex, keywordEndIndex, replacement);
        }
    }
}
