import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

kotlin {
    jvm("desktop")
    android()
    ios()

//    js(IR) {
//        browser()
//    }

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.Kotlin.testCommon)
                implementation(Deps.Kotlin.testAnnotationsCommon)

                // Decompose
                implementation(Deps.Decompose.decompose)

                // MVI
                implementation(Deps.MVIKotlin.rx)
                implementation(Deps.MVIKotlin.mvikotlin)

                // Koin
                implementation(Deps.Koin.core)

                implementation(Deps.Ktor.auth)
                implementation(Deps.Ktor.clientJson)
                implementation(Deps.Ktor.clientCore)
                implementation(Deps.Ktor.clientLogging)
                implementation(Deps.Ktor.clientSerialization)

                // Extras
                implementation(Deps.Extras.kermit)
                implementation(Deps.Serialization.json)
                implementation("co.touchlab:stately-common:1.1.7")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt") {
                    @Suppress("DEPRECATION")
                    isForce = true
                }
            }
        }

        named("androidMain") {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.3.0")
                implementation(Deps.AndroidX.core)
                implementation(compose.runtime)
                implementation(compose.material)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                implementation(Deps.Decompose.extensionsCompose)
                implementation(Deps.Ktor.clientAndroid)
                implementation(Deps.Koin.android)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
