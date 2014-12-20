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
import com.igitras.codegen.common.utils.TemplateUtils;
import com.igitras.codegen.common.utils.Utils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by mason on 2014-12-01.
 */
public abstract class AbstractJavaFilePart implements JavaFilePart {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJavaFilePart.class);
    protected final StringBuilder templateBuilder;
    private final String template;

    public AbstractJavaFilePart() {
        this(null);
    }

    public AbstractJavaFilePart(String templateName) {
        if (templateName == null) {
            this.template = resolveTemplate(Utils.getPathString(getClass()));
        } else {
            this.template = resolveTemplate(templateName);
        }
        this.templateBuilder = new StringBuilder(this.template);
    }

    @Override
    public String getTemplate() {
        return this.template;
    }

    @Override
    public String build() {
        if (!handleMultiplePart()) {
            TemplateUtils.processFields(this, templateBuilder);
        }
        return templateBuilder.toString();
    }

    @Override
    public void preBuild() {

    }

    @Override
    public void postBuild() {

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

    protected boolean handleMultiplePart() {
        if (JavaMultiplePart.class.isAssignableFrom(getClass())) {
            JavaMultiplePart multiplePart = (JavaMultiplePart) this;
            Assert.notNull(multiplePart, "Java Multiple Part must not be null.");
            Iterator<AbstractJavaFilePart> parts = multiplePart.getParts();
            StringBuilder builder = new StringBuilder();
            String elementTypeName = multiplePart.getElementType().getSimpleName();

            while (parts.hasNext()) {
                AbstractJavaFilePart next = parts.next();
                builder.append(next.build());
            }

            TemplateUtils.replaceMultipleJavaFilePartProperty(elementTypeName, builder.toString(), templateBuilder);
            return true;
        }
        return false;
    }

}

