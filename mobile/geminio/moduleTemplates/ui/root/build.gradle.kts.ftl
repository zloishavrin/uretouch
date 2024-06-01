import com.movies.buildlogic.convention.addComposeDependencies

plugins {
    alias(libs.plugins.android.library)
    id("convention.android")
    id("convention.compose")
}

android {
    namespace = "${packageName}"
}

dependencies {
    implementation(projects.common.uikit)
    implementation(projects.feature.modeSelection)
    addComposeDependencies()
}