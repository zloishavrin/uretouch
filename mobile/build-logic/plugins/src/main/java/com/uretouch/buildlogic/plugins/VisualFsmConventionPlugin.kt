package com.uretouch.buildlogic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

// Установка зависимостей для задач и директории для необходимых sourceSets - это временная конструкция пока в multiplatform плагине не подключаются автоматом sourceSet для KSP
// https://github.com/google/ksp/issues/567
// https://github.com/google/ksp/issues/965
internal class VisualFsmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.google.devtools.ksp")
        }

        dependencies {
            add("kspCommonMainMetadata", libs.findLibrary("kontur.mobile.visualfsm.compiler").get())
        }

        tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }

        kotlinExtension.sourceSets.all {
            if (name == "commonMain") {
                dependencies {
                    implementation(libs.findLibrary("kontur.mobile.visualfsm.core").get())
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                }
            }
        }
    }
}