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

import java.io.File;
import java.util.Iterator;

/**
 * Base interface the describe this as a container.
 * <p>
 * Created by mason on 12/17/14.
 */
public interface Containable<T extends Element> {

    /**
     * Get the Current Directory in file system.
     *
     * @return
     */
    File getDirectory();

    /**
     * Get all the sub elements in this containable.
     *
     * @return
     */
    Iterator<T> getElements();

    /**
     * Add sub element to this.
     *
     * @param elements
     */
    void addElement(T elements);

    /**
     * Remove sub element from this.
     *
     * @param element
     */
    void removeElement(T element);
}
