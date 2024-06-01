plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.uretouch.domain.onboarding.logic"
}
