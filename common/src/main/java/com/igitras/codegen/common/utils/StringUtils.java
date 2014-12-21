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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m00290368 on 2014-12-08.
 */
public abstract class StringUtils {

    public static <T> String join(String sign, List<T> src) {
        StringBuilder builder = new StringBuilder();
        if (src != null) {
            for (T string : src) {
                builder.append(string).append(sign);
            }

            if (builder.lastIndexOf(sign) > 0) {
                builder.delete(builder.lastIndexOf(sign), builder.length());
            }
        }
        return builder.toString();
    }

    public static String lastSection(String src, String splitter) {
        if (src == null) {
            return "";
        }
        String[] strings = src.split(splitter);
        if (strings.length > 0) {
            return strings[strings.length - 1];
        }
        return "";
    }

    public static boolean isClassName(String src) {
        if (null == src || src.isEmpty()) {
            return false;
        }

        return Character.isUpperCase(src.charAt(0));
    }

    public static String getClassName(String src) {
        if (isImportClass(src)) {
            return lastSection(src, "\\.");
        }
        return src;
    }

    public static boolean isImportClass(String src) {
        return isClassName(lastSection(src, "\\."));
    }

    public static <T> String commaJoin(List<T> classes) {
        return join(", ", classes);
    }

    public static List<String> getLastSectionList(List<String> src) {
        Assert.notNull(src);
        List<String> result = new ArrayList<String>(src.size());
        for (String string : src) {
            result.add(lastSection(string, "\\."));
        }
        return result;
    }

    public static String packageToPath(String packageName) {
        if (packageName == null) {
            packageName = "";
        }
        return packageName.replaceAll("\\.", "\\" + File.separator);
    }

    public static String concat(String sourceFolderName, String packageName) {
        return sourceFolderName + "[" + packageName + "]";
    }

    public static String getPackageName(String className) {
        Assert.hasLength(className, "Package Name must have length.");

        int lastIndexOf = className.lastIndexOf('.');
        if(lastIndexOf < 0){
            throw new IllegalArgumentException(String.format("Illegal Class name with name %s", className));
        }
        return className.substring(0, lastIndexOf);
    }
}
