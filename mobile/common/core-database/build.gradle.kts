plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    id("app.cash.sqldelight")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.sqlDelight.androidDriver)
            implementation(libs.koin.android)
        }

        commonMain.dependencies {
            implementation(projects.common.core)

            implementation(libs.koin.core)
            implementation(libs.kotlinx.datetime)
            api(libs.sqlDelight.coroutinesExtensions)
        }

        iosMain.dependencies {
            api(libs.sqlDelight.nativeDriver)
        }
    }
}

android {
    namespace = "com.uretouch.common.core.database"
}

sqldelight {
    databases {
        create("URetouchDatabase") {
            packageName.set("com.uretouch.common.core.database")
        }
    }
}