package com.uretouch.buildlogic.plugins.configurations

import com.android.build.gradle.BaseExtension
import com.uretouch.buildlogic.plugins.Versions
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureAndroid() {
    android {
        setCompileSdkVersion(Versions.compileSdk)

        defaultConfig {
            minSdk = Versions.minSdk

            if (pluginManager.hasPlugin("com.android.application")) {
                targetSdk = Versions.targetSdk
            }
        }

        compileOptions {
            sourceCompatibility = Versions.java
            targetCompatibility = Versions.java
        }

        kotlinOptions {
            jvmTarget = Versions.java.toString()
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
            }

            if (!pluginManager.hasPlugin("com.android.application")) {
                getByName("mock") { // https://github.com/cashapp/sqldelight/issues/3584
                    matchingFallbacks.addAll(listOf("debug"))
                }
                getByName("staging") { // https://github.com/cashapp/sqldelight/issues/3584
                    matchingFallbacks.addAll(listOf("release"))
                }
            }
        }
    }
}

internal fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure(action)

private fun BaseExtension.kotlinOptions(configure: Action<KotlinJvmOptions>) = (this as ExtensionAware).extensions.findByName("kotlinOptions")?.let {
    configure.execute(it as KotlinJvmOptions)
}