plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.core)
            implementation(projects.domain.auth)
            implementation(projects.domain.generations)

            implementation(projects.feature.history.logic)
            implementation(projects.feature.camera.logic)
            implementation(projects.feature.settings.logic)

            implementation(libs.koin.core)
            implementation(libs.decompose)
        }
    }
}

android {
    namespace = "com.uretouch.feature.tab.navigation.logic"
}
