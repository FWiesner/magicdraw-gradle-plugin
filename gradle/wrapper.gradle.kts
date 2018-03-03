apply {
    from(file("$rootDir/gradle/versions.gradle.kts"))
}

tasks {
    getByName("wrapper") {
        val t = this as Wrapper
        t.gradleVersion = gradleVersion
    }
}