// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.detekt.plugin) apply false
}

val detektVersion = libs.versions.detekt.get()
val detektComposeRules = libs.detekt.compose.rules

// core:data:account-api is a pure kotlin("jvm") module. Detekt 1.23.8's Kotlin-JVM task
// registration crashes on Kotlin 2.3.0 metadata (NoClassDefFoundError: KotlinJvmProjectExtension,
// see https://github.com/detekt/detekt/issues/8865, closed as won't-fix for the 1.23.x branch).
// The Android-plugin registration path used by every other module is unaffected. Re-include this
// module once Detekt 2.0 (plugin id "dev.detekt") is stable and adopted.
val detektExcludedProjects = setOf(":core:data:account-api")

subprojects {
    if (path in detektExcludedProjects) return@subprojects

    apply(plugin = "io.gitlab.arturbosch.detekt")

    extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        toolVersion = detektVersion
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        baseline = file("detekt-baseline.xml")
        buildUponDefaultConfig = true
        autoCorrect = true
    }

    dependencies {
        add("detektPlugins", detektComposeRules)
    }
}
