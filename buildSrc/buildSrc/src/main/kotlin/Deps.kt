object Deps {
    object Kotlin {
        // __KOTLIN_COMPOSE_VERSION__
        private const val VERSION = "1.5.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
        const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
        const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
        const val testJs = "org.jetbrains.kotlin:kotlin-test-js:$VERSION"
        const val testAnnotationsCommon =
            "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"
    }

    object Compose {
        // __LATEST_COMPOSE_RELEASE_VERSION__
        private const val VERSION = "0.5.0-build225"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        const val material = "androidx.compose.material:material:$VERSION"
    }

    object Google {
        object Accompanist {
            const val VERSION = "0.13.0"
            const val pager = "com.google.accompanist:accompanist-pager:$VERSION"
            const val pagerIndicators =
                "com.google.accompanist:accompanist-pager-indicators:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:4.0.1"
            }
        }
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.5.0"

        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.3.0-beta01"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha02"
        }
    }

    object MultiPlatformSettings {
        const val noarg = "com.russhwolf:multiplatform-settings-no-arg:0.7.7"
    }

    object Koin {
        private const val VERSION = "3.1.2"
        const val core = "io.insert-koin:koin-core:${VERSION}"
        const val test = "io.insert-koin:koin-test:${VERSION}"
        const val android = "io.insert-koin:koin-android:${VERSION}"
        const val compose = "io.insert-koin:koin-androidx-compose:${VERSION}"
    }

    object Ktor {
        private const val VERSION = "1.6.0"
        private const val slf4jVer = "1.7.31"
        const val clientCore = "io.ktor:ktor-client-core:${VERSION}"
        const val clientJson = "io.ktor:ktor-client-json:${VERSION}"
        const val clientLogging = "io.ktor:ktor-client-logging:${VERSION}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${VERSION}"

        const val auth = "io.ktor:ktor-client-auth:${VERSION}"
        const val clientAndroid = "io.ktor:ktor-client-android:${VERSION}"
        const val clientCurl = "io.ktor:ktor-client-curl:${VERSION}"
        const val clientApache = "io.ktor:ktor-client-apache:${VERSION}"
        const val slf4j = "org.slf4j:slf4j-simple:${slf4jVer}"
        const val clientIos = "io.ktor:ktor-client-ios:${VERSION}"
        const val clientCio = "io.ktor:ktor-client-cio:${VERSION}"
        const val clientJs = "io.ktor:ktor-client-js:${VERSION}"
    }

    object MVIKotlin {
        private const val VERSION = "2.0.3"
        const val rx = "com.arkivanov.mvikotlin:rx:$VERSION"
        const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
        const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
        const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
        const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
        const val mvikotlinExtensionsReaktive =
            "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$VERSION"
    }

    object Decompose {
        private const val VERSION = "0.2.6"
        const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
        const val extensionsCompose =
            "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
    }

    object Badoo {
        object Reaktive {
            private const val VERSION = "1.1.22"
            const val reaktive = "com.badoo.reaktive:reaktive:$VERSION"
            const val reaktiveTesting = "com.badoo.reaktive:reaktive-testing:$VERSION"
            const val utils = "com.badoo.reaktive:utils:$VERSION"
            const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$VERSION"
        }
    }

    object Squareup {
        object SQLDelight {
            private const val VERSION = "1.5.0"

            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"
            const val androidDriver = "com.squareup.sqldelight:android-driver:$VERSION"
            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$VERSION"
            const val nativeDriver = "com.squareup.sqldelight:native-driver:$VERSION"
            const val sqljsDriver = "com.squareup.sqldelight:sqljs-driver:$VERSION"
        }
    }

    object Serialization {
        private const val VERSION =  "1.2.1"
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${VERSION}"
    }

    object Extras {
        private const val KERMIT_VER = "0.1.9"
        const val kermit = "co.touchlab:kermit:${KERMIT_VER}"
    }
}
