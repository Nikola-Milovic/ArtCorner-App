plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:main"))
                implementation(project(":common:create"))
                implementation(project(":common:details"))
//                implementation(project(":common:edit"))
                implementation(project(":common:root"))
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)

                implementation(Deps.Google.Accompanist.pager)
            }
        }

        named("androidMain") {
            dependencies {
                implementation(Deps.JetBrains.Compose.material)
                implementation(Deps.Google.Accompanist.pager)
                implementation(Deps.Google.Accompanist.pagerIndicators)
            }
        }
    }
}
