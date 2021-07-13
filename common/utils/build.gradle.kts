plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.MVIKotlin.rx)
                implementation(Deps.MVIKotlin.mvikotlin)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }
    }
}
