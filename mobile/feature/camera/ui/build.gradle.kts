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
            implementation(projects.feature.camera.logic)

            implementation(libs.peekaboo.ui)
            implementation(libs.peekaboo.image.picker)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(compose.runtime)
            implementation(compose.material)
            implementation(compose.components.resources)

            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
//            implementation("io.ktor:ktor-client-android:[ktor-version]")
        }
    }
}

android {
    namespace = "com.uretouch.feature.camera.ui"
}
