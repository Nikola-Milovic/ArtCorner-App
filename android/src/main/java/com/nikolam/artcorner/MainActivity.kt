package com.nikolam.artcorner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import nikolam.artcorner.common.data.GroupApi
import nikolam.artcorner.common.root.ArtRoot
import nikolam.artcorner.common.root.integration.ArtRootComponent
import nikolam.artcorner.common.ui.ArtRootContent
import nikolam.artcorner.common.di.preference.PreferenceManager
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val groupApi: GroupApi by inject()
    private val preferenceManager: PreferenceManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ArtRootContent(rememberRootComponent(::artRoot))
                }
            }
        }
    }

    private fun artRoot(componentContext: ComponentContext): ArtRoot =
        ArtRootComponent(
            componentContext = componentContext,
            dependencies = object : ArtRoot.Dependencies {
                override val groupApi = this@MainActivity.groupApi
                override val preferenceManager: PreferenceManager = this@MainActivity.preferenceManager
                override val storeFactory: StoreFactory = LoggingStoreFactory(DefaultStoreFactory)
            },
        )
}
