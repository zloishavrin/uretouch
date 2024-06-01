plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.auth.logic)
            implementation(projects.feature.auth.ui.auth)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(compose.runtime)
            implementation(compose.material)
        }
    }
}

android {
    namespace = "com.uretouch.feature.auth.ui.root"
}
