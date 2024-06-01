package com.uretouch.buildlogic.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.uretouch.buildlogic.plugins.configurations.android
import com.uretouch.buildlogic.plugins.configurations.configureAndroid
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

internal class MultiplatformConventionPlugin : Plugin<Project> {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }

        android {
            buildTypes {
                create("mock") {
                    isMinifyEnabled = false
                }
                create("staging") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
                named("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        var hasTestClassesTask = false
        tasks.configureEach {
            if (name == "testClasses") {
                hasTestClassesTask = true
                return@configureEach
            }
        }

        if (!hasTestClassesTask) {
            task("testClasses")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            if (pluginManager.hasPlugin("com.android.library") || pluginManager.hasPlugin("com.android.application")) {
                androidTarget {
                    compilations.all {
                        compileTaskProvider.configure {
                            compilerOptions {
                                jvmTarget.set(JvmTarget.JVM_17)
                                freeCompilerArgs.add("-Xjdk-release=${JavaVersion.VERSION_17}")
                            }
                        }
                    }
                }
            }

            iosX64()
            iosArm64()
            iosSimulatorArm64()

            compilerOptions {
                // Suppress expect/actual warnings
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }

        if (pluginManager.hasPlugin("com.android.library") || pluginManager.hasPlugin("com.android.application")) {
            configureAndroid()
        }
    }
}