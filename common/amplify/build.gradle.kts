import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("android-setup")
}


// CocoaPods requires the podspec to have a version.
version = "1.0"


kotlin {

    android()
    ios()

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utils"))
//                implementation(project(":common:database"))
                implementation(Deps.Decompose.decompose)
                implementation(Deps.MVIKotlin.mvikotlin)
                implementation(Deps.MVIKotlin.mvikotlinExtensionsReaktive)
                implementation(Deps.Badoo.Reaktive.reaktive)
                implementation(Deps.Badoo.Reaktive.coroutinesInterop)
            }
        }
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Amplify wrapper for KMP project"
        homepage = "Link to a Kotlin/Native module homepage"

        // You can change the name of the produced framework.
        // By default, it is the name of the Gradle project.
        frameworkName = "AmplifyKMP"

        pod("Amplify")
        pod("AmplifyPlugins/AWSCognitoAuthPlugin")
        pod("AmplifyPlugins/AWSPinpointAnalyticsPlugin")
    }

    // Configure the framework which is generated internally by cocoapods plugin
    targets.withType<KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = true
            transitiveExport = true
        }
    }
}
