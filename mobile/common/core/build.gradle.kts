plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.decompose)

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.koin.core)
        }
    }
}
android {
    namespace = "com.uretouch.common.core"
    buildFeatures.buildConfig = true
}
