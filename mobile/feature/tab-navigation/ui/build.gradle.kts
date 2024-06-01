plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.uiKit)
            implementation(projects.feature.tabNavigation.logic)

            implementation(projects.feature.history.logic)
            implementation(projects.feature.history.ui)
            implementation(projects.feature.camera.logic)
            implementation(projects.feature.camera.ui)
            implementation(projects.feature.settings.logic)
            implementation(projects.feature.settings.ui)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(compose.runtime)
            implementation(compose.material)
        }
    }
}

android {
    namespace = "com.uretouch.feature.tab.navigation.ui"
}
