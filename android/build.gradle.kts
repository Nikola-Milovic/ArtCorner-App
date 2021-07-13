import org.jetbrains.compose.compose

plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/*")
    }
}

dependencies {
//    implementation(project(":common:database"))
//    implementation(project(":common:utils"))
    implementation(project(":common:root"))
    implementation(project(":common:di"))
    implementation(project(":common:compose-ui"))

    // Koin
    implementation(Deps.Koin.android)
    implementation(Deps.Koin.compose)

    implementation(compose.material)

    implementation(Deps.MVIKotlin.mvikotlin)
    implementation(Deps.MVIKotlin.mvikotlinMain)
    implementation(Deps.MVIKotlin.mvikotlinLogging)
    implementation(Deps.MVIKotlin.mvikotlinTimeTravel)
    implementation(Deps.Decompose.decompose)
    implementation(Deps.Decompose.extensionsCompose)
    implementation(Deps.AndroidX.AppCompat.appCompat)
    implementation(Deps.AndroidX.Activity.activityCompose)
}
