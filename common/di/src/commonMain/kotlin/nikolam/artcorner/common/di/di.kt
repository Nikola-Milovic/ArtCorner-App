package nikolam.artcorner.common.di

import co.touchlab.kermit.Kermit
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import nikolam.artcorner.common.di.preference.PreferenceManager
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.native.concurrent.SharedImmutable
import kotlin.native.concurrent.ThreadLocal

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            commonModule(enableNetworkLogs = enableNetworkLogs)
        )
    }

// Called by IOS
fun initKoin() = initKoin(enableNetworkLogs = false) { }

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { createHttpClient(enableNetworkLogs = enableNetworkLogs) }
    single { PreferenceManager(get()) }
    single { Kermit(getLogger()) }
    single { Settings() }
}

@ThreadLocal
val globalJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun createHttpClient(enableNetworkLogs: Boolean = false) = HttpClient {
    // https://github.com/Kotlin/kotlinx.serialization/issues/1450
    install(JsonFeature) {
        serializer = KotlinxSerializer(globalJson)
    }
    install(HttpTimeout) {
        socketTimeoutMillis = 520_000
        requestTimeoutMillis = 360_000
        connectTimeoutMillis = 360_000
    }
    // WorkAround for Freezing
    // Use httpClient.getData / httpClient.postData Extensions
    /*install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }*/
    if (enableNetworkLogs) {
        install(Logging) {
            level = LogLevel.INFO
        }
    }
}

/*Client Active Throughout App's Lifetime*/
@SharedImmutable
val ktorHttpClient = HttpClient {}
