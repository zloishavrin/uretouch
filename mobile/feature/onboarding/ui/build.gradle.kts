plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.compose")
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(projects.common.uiKit)
            implementation(projects.feature.onboarding.logic)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(compose.runtime)
            implementation(compose.material)
            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "com.uretouch.feature.onboarding.ui"
}
