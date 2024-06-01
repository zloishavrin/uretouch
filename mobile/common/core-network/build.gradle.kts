plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.okHttp)
        }

        commonMain.dependencies {
            implementation(projects.common.core)

            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.auth)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.json)

            implementation(libs.koin.core)

            implementation(libs.napier)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}

android {
    namespace = "com.uretouch.common.core.network"
}
