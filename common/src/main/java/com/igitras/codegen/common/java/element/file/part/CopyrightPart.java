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

import java.util.Calendar;
import java.util.Collections;
import java.util.Set;

/**
 * Created by m00290368 on 2014-12-02.
 */
public class CopyrightPart extends AbstractJavaFilePart {
    private final Integer copyrightYear;
    private final String companyName;

    public CopyrightPart() {
        this("igitras.com");
    }

    public CopyrightPart(String companyName) {
        this(Calendar.getInstance().get(Calendar.YEAR), companyName);
    }

    public CopyrightPart(Integer copyrightYear, String companyName) {
        this.copyrightYear = copyrightYear;
        this.companyName = companyName;
    }

    public Integer getCopyrightYear() {
        return copyrightYear;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public Set<JavaImportPart> getImports() {
        return Collections.emptySet();
    }
}
