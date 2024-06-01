package com.uretouch.buildlogic.plugins

import com.uretouch.buildlogic.plugins.configurations.android
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jetbrains.compose")
        }

        android {
            buildFeatures.compose = true
            composeOptions {
                kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().requiredVersion
            }
        }
    }
}