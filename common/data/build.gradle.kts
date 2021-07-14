import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("plugin.serialization") version "1.5.10"
}

kotlin {
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

    targets.getByName<KotlinNativeTarget>("iosX64").compilations.forEach {
        it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
    }
}
