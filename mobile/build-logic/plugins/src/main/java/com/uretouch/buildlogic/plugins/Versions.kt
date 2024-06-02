package com.uretouch.buildlogic.plugins

import org.gradle.api.JavaVersion

internal object Versions {
    const val minSdk = 29
    const val compileSdk = 34
    const val targetSdk = 34
    val java = JavaVersion.VERSION_17
}