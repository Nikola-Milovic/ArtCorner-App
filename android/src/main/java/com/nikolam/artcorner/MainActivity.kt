package com.nikolam.artcorner

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import nikolam.artcorner.common.root.ArtRoot
import nikolam.artcorner.common.root.integration.ArtRootComponent
import nikolam.artcorner.common.ui.ArtRootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ArtRootContent(rememberRootComponent(::todoRoot))
                }
            }
        }
    }

    private fun todoRoot(componentContext: ComponentContext): ArtRoot =
        ArtRootComponent(
            componentContext = componentContext,
            storeFactory = LoggingStoreFactory(TimeTravelStoreFactory(DefaultStoreFactory)),
        )
}
