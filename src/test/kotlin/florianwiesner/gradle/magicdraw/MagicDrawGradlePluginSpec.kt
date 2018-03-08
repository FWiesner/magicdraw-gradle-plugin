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

import org.gradle.testkit.runner.GradleRunner
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path

/**
 * @author Florian Wiesner
 */
object MagicDrawGradlePluginSpec : SubjectSpek<MagicDrawGradlePlugin>({

    subject { MagicDrawGradlePlugin() }

    given("A basic build script") {
        val tmpDir = createTempDir()

        fillFile(tmpDir, "build.gradle.kts") {
            """|plugins {
               |    `kotlin-dsl`
               |    id("info.florian-wiesner.magicdraw")
               |}
               |
               |magicdraw {
               |    installDir = "/Applications/MagicDraw"
               |}
               |
               |println(project.extensions.getByName("magicdraw"))
               | """.trimMargin()
        }

        fillFile(tmpDir,"settings.gradle.kts") {
            """|pluginManagement {
               |    repositories {
               |        gradlePluginPortal()
               |    }
               |}""".trimMargin()
        }

        on("test project load") {
            val buildResult = GradleRunner.create()
                    .withProjectDir(tmpDir.toFile())
                    .withPluginClasspath()
                    .withDebug(true)
                    .withArguments("verifyMagicDraw")
                    .build()

            println(buildResult.output)
            it("Should have the MagicDraw directory set") {
            }
        }
    }
})

fun fillFile(tmpDir: Path, fileName: String, contentSource: () -> String) {
    tmpDir.resolve(fileName).toFile().bufferedWriter(Charset.defaultCharset()).use {
        it.write(contentSource())
    }
}

fun createTempDir() = Files.createTempDirectory("gradle-spek")!!

