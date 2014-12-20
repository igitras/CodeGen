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

package com.igitras.codegen.common.java.element;

import com.igitras.codegen.common.AbstractElement;
import com.igitras.codegen.common.Containable;
import com.igitras.codegen.common.File;
import com.igitras.codegen.common.Template;
import com.igitras.codegen.common.java.element.enums.IsAbstract;
import com.igitras.codegen.common.java.element.enums.IsFinal;
import com.igitras.codegen.common.java.element.enums.VisitPrivilege;
import com.igitras.codegen.common.utils.Assert;
import com.igitras.codegen.common.utils.Reformatter;
import com.igitras.codegen.common.utils.TemplateUtils;
import com.igitras.codegen.common.utils.Utils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mason on 12/17/14.
 */
public abstract class JavaFile extends AbstractElement implements JavaElement, File, Template {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaFile.class);
    protected final StringBuilder templateBuilder;
    private final Containable containable;
    private final String template;

    public JavaFile(String name, Containable containable) {
        this(name, containable, null);
    }

    public JavaFile(String name, Containable containable, String templateName) {
        super(name);
        Assert.notNull(containable, "Parent Containable must not be null.");
        this.containable = containable;
        if (templateName == null) {
            this.template = resolveTemplate(Utils.getPathString(getClass()));
        } else {
            this.template = resolveTemplate(templateName);
        }
        this.templateBuilder = new StringBuilder(this.template);
    }

    @Override
    public Containable getContainable() {
        return this.containable;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public String build() {
        preBuild();

        collectImports();

        TemplateUtils.processFields(this, templateBuilder);

        postBuild();
        return Reformatter.fixup(templateBuilder.toString());
    }

    @Override
    public String resolveTemplate(String templatePath) {
        LOGGER.info("Start to resolve template with name {}", templatePath);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templatePath)) {
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException(
                    String.format("Cannot find template with name %s within current package.", templatePath));
        }
    }

    protected abstract void collectImports();

    public abstract void withComment(String comment);

    public abstract void withPrivilege(VisitPrivilege privilege);

    public abstract void withAbstract(IsAbstract isAbstract);

    public abstract void withFinal(IsFinal isFinal);

    @Override
    public String getSuffix() {
        return ".java";
    }
}
