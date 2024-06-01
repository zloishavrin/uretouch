plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.multiplatformSettings)
        }
    }
}

android {
    namespace = "com.uretouch.common.core.settings"
}
