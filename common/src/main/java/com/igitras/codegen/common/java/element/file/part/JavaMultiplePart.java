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

import java.util.Iterator;

/**
 * Created by m00290368 on 2014-12-05.
 */
public interface JavaMultiplePart<T extends AbstractJavaFilePart> {

    /**
     * Get all the element parts iterator.
     *
     * @return
     */
    Iterator<T> getParts();

    /**
     * Add parts to the element parts.
     *
     * @param parts
     */
    void addParts(T... parts);

    Class<T> getElementType();
}
