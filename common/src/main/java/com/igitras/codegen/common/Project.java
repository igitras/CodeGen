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
 * Base Interface as a Project, can be any type of projects.
 * <p>
 * Created by mason on 12/17/14.
 */
public interface Project {

    /**
     * Get the Project structure description.
     *
     * @return The Project Structure
     */
    Structure getProjectStructure();

    /**
     * Get the project or module configuration.
     *
     * @return The Project Configuration.
     */
    Configuration getConfiguration();

    /**
     * Generate Project.
     */
    void generate();
}
