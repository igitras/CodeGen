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
public abstract class AbstractStructure<D extends Directory, F extends File> implements Structure<D, F> {

    protected final Map<String, D> directoryNameMap = new HashMap<String, D>();
    protected final Map<String, F> fileNameMap = new HashMap<String, F>();

    @Override
    public D getDirectory(String directoryName) {
        return directoryNameMap.get(directoryName);
    }

    @Override
    public void removeDirectory(String directoryName) {
        if(directoryNameMap.containsKey(directoryName)){
            directoryNameMap.remove(directoryName);
        }
    }

    @Override
    public void addDirectory(String directoryName, D directory) {
        if (directoryNameMap.get(directoryName) != null) {
            throw new InvalidStateException(String.format("Directory with name %s already Exist.", directoryName));
        }

        directoryNameMap.put(directoryName, directory);
    }


    @Override
    public F getFile(String fileName) {
        return fileNameMap.get(fileName);
    }


    @Override
    public void addFile(String fileName, F file) {
        if(fileNameMap.get(fileName) != null) {
            throw new InvalidStateException(String.format("File with name %s already exist.", fileName));
        }
        fileNameMap.put(fileName, file);
    }

    @Override
    public void removeFile(String fileName) {
        if(fileNameMap.containsKey(fileName)){
            fileNameMap.remove(fileName);
        }
    }
}
