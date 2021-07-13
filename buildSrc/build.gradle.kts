plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(Deps.Compose.gradlePlugin)
    implementation(Deps.Kotlin.gradlePlugin)
    implementation(Deps.Android.Tools.Build.gradlePlugin)
    implementation(Deps.Squareup.SQLDelight.gradlePlugin)
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
