plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.visualFsm")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.core)

            implementation(projects.domain.auth)

            implementation(libs.koin.core)
            implementation(libs.decompose)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "com.uretouch.feature.settings.logic"
}
