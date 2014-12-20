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

/**
 * Base interface of the project elements. Such as file or directory. Will be mapped to the File system file or Directory.
 * <p>
 * Created by mason on 12/17/14.
 */
public interface Element {

    /**
     * The name of this elements. Must be unique in parent folder.
     *
     * @return
     */
    String getName();

    /**
     * Construct this element in the parent folder.
     * <p>
     * If this is an folder element, recursive create the sub elements.
     */
    void construct();

}
