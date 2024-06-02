plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("convention.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            all {
                languageSettings {
                    optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
                }
            }
            implementation(projects.common.uiKit)
            implementation(projects.common.core)
            implementation(projects.feature.history.logic)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(compose.runtime)
            implementation(compose.material)
            implementation(compose.components.resources)

            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
        }
    }
}

android {
    namespace = "com.uretouch.feature.history.ui"
}
