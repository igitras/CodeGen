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

import java.util.Collections;
import java.util.Set;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class JavaEnumElementsPart extends AbstractJavaFilePart {

    private final JavaCommaSplitPart commaSplitList;

    public JavaEnumElementsPart() {
        this(new JavaCommaSplitPart());
    }

    public JavaEnumElementsPart(JavaCommaSplitPart commaSplitList) {
        Assert.notNull(commaSplitList, "Java Comma Split List Part must not be null.");
        this.commaSplitList = commaSplitList;
    }

    public JavaCommaSplitPart getCommaSplitList() {
        return commaSplitList;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        return Collections.unmodifiableSet(commaSplitList.getImports());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaEnumElementsPart)) {
            return false;
        }

        JavaEnumElementsPart that = (JavaEnumElementsPart) o;

        if (commaSplitList != null ? !commaSplitList.equals(that.commaSplitList) : that.commaSplitList != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return commaSplitList != null ? commaSplitList.hashCode() : 0;
    }
}
