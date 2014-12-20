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

import java.util.Set;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class JavaThrowsPart extends AbstractJavaFilePart {

    private final JavaCommaSplitPart commaSplitList;

    public JavaThrowsPart() {
        this(new JavaCommaSplitPart());
    }

    public JavaThrowsPart(JavaCommaSplitPart commaSplitList) {
        Assert.notNull(commaSplitList, "Java Comma Split List Part must not be null.");
        this.commaSplitList = commaSplitList;
    }

    public JavaCommaSplitPart getCommaSplitList() {
        return commaSplitList;
    }

    @Override
    public String build() {
        if (!commaSplitList.hasEntry()) {
            return "";
        }
        return super.build();
    }

    @Override
    public Set<JavaImportPart> getImports() {
        return commaSplitList.getImports();
    }
}
