plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.uiKit)
            implementation(projects.common.core)
            implementation(projects.feature.history.logic)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(compose.runtime)
            implementation(compose.material)
        }
    }
}

android {
    namespace = "com.uretouch.feature.history.ui"
}
