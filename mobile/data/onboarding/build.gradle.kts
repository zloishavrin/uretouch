plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.coreSettings)

            implementation(projects.domain.onboarding.logic)

            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.uretouch.data.onboarding"
}
