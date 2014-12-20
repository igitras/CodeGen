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

import sun.plugin.dom.exception.InvalidStateException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mason on 12/18/14.
 */
public abstract class AbstractStructure implements Structure {

    protected final Map<String, Directory> directoryNameMap = new HashMap<String, Directory>();
    protected final Map<String, File> fileNameMap = new HashMap<String, File>();

    @Override
    public Directory getDirectory(String directoryName) {
        return directoryNameMap.get(directoryName);
    }

    @Override
    public File getFile(String fileName) {
        return fileNameMap.get(fileName);
    }

    @Override
    public void addDirectory(String concatPackageName, Directory directory) {
        if (directoryNameMap.get(concatPackageName) != null) {
            throw new InvalidStateException("Java Package already Exist.");
        }

        directoryNameMap.put(concatPackageName, directory);
    }

}
