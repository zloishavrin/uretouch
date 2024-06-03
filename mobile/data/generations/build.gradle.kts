plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.core)
            implementation(projects.common.coreDatabase)
            implementation(projects.common.coreNetwork)

            implementation(projects.domain.generations)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.uretouch.data.generations"
}
