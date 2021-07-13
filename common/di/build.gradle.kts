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
            api(Deps.MultiPlatformSettings.noarg)
                implementation(Deps.MVIKotlin.rx)
            }
        }

        named("commonTest") {
            dependencies {

            }
        }
    }

    targets.getByName<KotlinNativeTarget>("iosX64").compilations.forEach {
        it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
    }
}
