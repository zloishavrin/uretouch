plugins {
    alias(libs.plugins.androidLibrary)
    id("convention.multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.core)
            implementation(projects.common.coreSettings)
            implementation(projects.common.coreNetwork)

            implementation(projects.feature.auth.logic)
            implementation(projects.feature.tabNavigation.logic)
            implementation(projects.feature.onboarding.logic)

            implementation(projects.data.onboarding)
            implementation(projects.data.auth)

            implementation(projects.domain.onboarding.logic)
            implementation(projects.domain.auth)

            implementation(libs.koin.core)
            implementation(libs.decompose)
        }
    }
}

android {
    namespace = "com.uretouch.feature.root.logic"
}
