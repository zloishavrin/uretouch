enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "URetouch"
include(":composeApp")
include(":common:ui-kit")
include(":common:core")
include(":common:core-network")
include(":feature:root")
include(":feature:auth")
include(":feature:auth:logic")
include(":feature:auth:ui")
include(":feature:auth:ui:root")
include(":feature:auth:ui:auth")
include(":feature:root:logic")
include(":feature:root:ui")
include(":common:core-settings")
include(":feature:onboarding:logic")
include(":feature:onboarding:ui")
include(":domain:onboarding")
include(":domain:onboarding:logic")
include(":data:onboarding")
