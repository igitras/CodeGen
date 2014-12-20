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

package com.igitras.codegen.common.java.element.file;

import com.igitras.codegen.common.FileTemplate;
import com.igitras.codegen.common.java.element.file.part.JavaAbstractMethodPart;
import com.igitras.codegen.common.java.element.file.part.JavaConstructorPart;
import com.igitras.codegen.common.java.element.file.part.JavaFieldPart;
import com.igitras.codegen.common.java.element.file.part.JavaMethodPart;

/**
 * Created by mason on 2014-12-01.
 */
public interface JavaFileInterface extends FileTemplate {

    void addField(JavaFieldPart fieldPart);

    void removeField(JavaFieldPart fieldPart);

    void addAbstractMethod(JavaAbstractMethodPart abstractMethodPart);

    void removeAbstractMethod(JavaAbstractMethodPart abstractMethodPart);

    void addConstructor(JavaConstructorPart constructorPart);

    void removeConstructor(JavaConstructorPart constructorPart);

    void addMethod(JavaMethodPart methodPart);

    void removeMethod(JavaMethodPart methodPart);
}

