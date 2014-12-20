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
import com.igitras.codegen.common.utils.StringUtils;
import com.igitras.codegen.common.utils.Utils;

import java.util.*;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class JavaCommaSplitPart extends AbstractJavaFilePart {

    private final List<JavaCommaSplitEntry> entries = new ArrayList<JavaCommaSplitEntry>();

    public JavaCommaSplitPart() {
        this(new ArrayList<JavaCommaSplitEntry>());
    }

    public JavaCommaSplitPart(List<JavaCommaSplitEntry> entries) {
        Assert.notNull(entries, "Java Comma Split Part must not be null.");
        addEntries(entries.toArray(new JavaCommaSplitEntry[entries.size()]));
    }

    @Override
    public String build() {
        return StringUtils.commaJoin(entries);
    }

    public void addEntries(JavaCommaSplitEntry... entries) {
        if (entries.length > 0) {
            for (JavaCommaSplitEntry entry : entries) {
                if (!this.entries.contains(entry)) {
                    this.entries.add(entry);
                }
            }
        }
    }

    public boolean hasEntry() {
        return entries.size() > 0;
    }

    public Iterator<JavaCommaSplitEntry> getEntries() {
        return entries.iterator();
    }

    @Override
    public Set<JavaImportPart> getImports() {
        Set<JavaImportPart> result = new HashSet<JavaImportPart>();
        for (JavaCommaSplitEntry entry : entries) {
            result.add(new JavaImportPart(entry.getClassName()));
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaCommaSplitPart)) {
            return false;
        }

        JavaCommaSplitPart that = (JavaCommaSplitPart) o;

        if (Utils.checkCollectionEqual(entries, that.entries)) {
            ;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return entries != null ? entries.hashCode() : 0;
    }
}
