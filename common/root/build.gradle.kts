import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {
    ios {
        binaries {
            framework {
                baseName = "ArtCorner"
                linkerOpts.add("-lsqlite3")
//                export(project(":common:database"))
                export(project(":common:main"))
//                export(project(":common:edit"))
                export(Deps.Decompose.decompose)
                export(Deps.MVIKotlin.mvikotlinMain)
            }
        }
    }

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utils"))
                //         implementation(project(":common:database"))
                implementation(project(":common:main"))
//                implementation(project(":common:edit"))
                implementation(Deps.MVIKotlin.mvikotlin)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }
    }

    sourceSets {
        named("iosMain") {
            dependencies {
                //      api(project(":common:database"))
                api(project(":common:main"))
                //             api(project(":common:edit"))
                api(Deps.Decompose.decompose)
                api(Deps.MVIKotlin.mvikotlinMain)
            }
        }
    }
}

fun getIosTarget(): String {
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"

    return if (sdkName.startsWith("iphoneos")) "iosArm64" else "iosX64"
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val targetName = getIosTarget()
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from(framework.outputDirectory)
    into(targetDir)
}
