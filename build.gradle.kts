import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
}

apply {
    from(file("gradle/versions.gradle.kts"))
    from(file("gradle/wrapper.gradle.kts"))
}

repositories {
    mavenCentral()
    jcenter()
}

group = "info.florian-wiesner"
version = "0.1-dev"

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.2.30"
    java
}

val kotlinVersion: String by extra

dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            targetCompatibility = "1.8"
        }
    }
}