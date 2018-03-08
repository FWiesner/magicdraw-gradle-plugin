import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.junit.platform.gradle.plugin.JUnitPlatformPlugin


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
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.1.0")
    }
}

apply {
    from(file("gradle/versions.gradle.kts"))
    from(file("gradle/wrapper.gradle.kts"))

    plugin<JUnitPlatformPlugin>()
}


repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

group = "info.florian-wiesner"
version = "0.1-dev"

plugins {
    kotlin("jvm") version "1.2.30"
    java
//    id("org.gradle.kotlin.kotlin-dsl") version "0.14.0"
    id("org.gradle.java-gradle-plugin")
}

val kotlinVersion: String by extra
val spekVersion: String by extra
val kluentVersion: String by extra
val mockitoKotlinVersion: String by extra

dependencies {
    compile(group="org.jetbrains.kotlin", name ="kotlin-stdlib-jdk8", version = kotlinVersion)
    compile(group="org.jetbrains.kotlin", name ="kotlin-reflect", version = kotlinVersion)

    testCompile(gradleTestKit())
    testCompile(group = "org.jetbrains.spek", name = "spek-api", version = spekVersion) {
        exclude(group = "org.jetbrains.kotlin")
    }
    testCompile(group = "org.jetbrains.spek", name = "spek-subject-extension", version = spekVersion)
    testCompile(group = "org.amshove.kluent", name = "kluent", version = kluentVersion)
    testCompile(group = "com.nhaarman", name = "mockito-kotlin", version = mockitoKotlinVersion)

    testImplementation(group = "org.jetbrains.spek", name = "spek-junit-platform-engine", version = spekVersion) {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "org.junit.platform")
    }
}

gradlePlugin {
    (plugins) {
        "magicDrawPlugin"{
            id = "info.florian-wiesner.magicdraw"
            implementationClass = "florianwiesner.gradle.magicdraw.MagicDrawGradlePlugin"
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            targetCompatibility = "1.8"
        }
    }
}