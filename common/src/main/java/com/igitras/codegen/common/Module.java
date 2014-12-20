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
 * Sub type of Project, if one project is multiple module projects, it will contains plenty of this.
 * <p>
 * Created by mason on 12/17/14.
 */
public interface Module extends Project {

    /**
     * The parent project or module of this module.
     *
     * @return The parent project of this module.
     */
    Project getParent();
}
