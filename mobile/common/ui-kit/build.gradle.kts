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

        androidMain.dependencies {
            implementation(libs.androidx.activityCompose)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.material)
            implementation(compose.components.resources)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.uretouch.common.ui.kit.resources"
    generateResClass = always
}

android {
    namespace = "com.uretouch.common.ui.kit"
}
