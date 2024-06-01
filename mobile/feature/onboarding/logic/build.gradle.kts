plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.visualFsm")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.core)

            implementation(projects.domain.onboarding.logic)

            implementation(libs.koin.core)
            implementation(libs.decompose)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "com.uretouch.feature.onboarding.logic"
}
