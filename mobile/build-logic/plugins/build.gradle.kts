plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.kotlinGradlePlugin)
}

gradlePlugin {
    plugins {
        register("convention.compose") {
            id = "convention.compose"
            implementationClass = "com.uretouch.buildlogic.plugins.ComposeConventionPlugin"
        }
        register("convention.multiplatform") {
            id = "convention.multiplatform"
            implementationClass = "com.uretouch.buildlogic.plugins.MultiplatformConventionPlugin"
        }
        register("convention.visualFsm") {
            id = "convention.visualFsm"
            implementationClass = "com.uretouch.buildlogic.plugins.VisualFsmConventionPlugin"
        }
    }
}