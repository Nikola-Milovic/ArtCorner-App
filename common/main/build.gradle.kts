import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:create"))
                implementation(project(":common:details"))
                implementation(project(":common:data"))
                implementation(project(":common:di"))
//                implementation(project(":common:database"))
                implementation(Deps.Decompose.decompose)
                implementation(Deps.MVIKotlin.mvikotlin)
                implementation(Deps.MVIKotlin.mvikotlinExtensionsReaktive)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }

        named("commonTest") {
            dependencies {
                implementation(Deps.MVIKotlin.mvikotlinMain)
                implementation(Deps.Badoo.Reaktive.reaktiveTesting)
                implementation(Deps.Badoo.Reaktive.utils)
            }
        }
    }

    targets.getByName<KotlinNativeTarget>("iosX64").compilations.forEach {
        it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
    }
}
