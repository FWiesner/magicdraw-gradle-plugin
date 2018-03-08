/*
 * Copyright 2018 Florian Wiesner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package florianwiesner.gradle.magicdraw

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger(MagicDrawExtension::class.java)

/**
 * @author Florian Wiesner
 */
class MagicDrawGradlePlugin : Plugin<Project> {
    private lateinit var project: Project

    override fun apply(project: Project?) {
        if (project == null) throw GradleException("project must not be null")
        this.project = project
        val extension = addExtension<MagicDrawExtension>("magicdraw", "Foo")

        val verifyMagicDrawTask = project.tasks.create("verifyMagicDraw", VerifyMagicDrawTask::class.java)
        verifyMagicDrawTask.installDir = extension.installDir
    }

    private inline fun <reified T> addExtension(name: String, vararg args: Any): T = project.extensions.create(name, T::class.java, *args)

}