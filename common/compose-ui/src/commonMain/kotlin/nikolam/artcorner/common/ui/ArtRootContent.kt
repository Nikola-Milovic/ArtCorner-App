@file:Suppress("EXPERIMENTAL_API_USAGE")

package nikolam.artcorner.common.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import nikolam.artcorner.common.root.ArtRoot
import nikolam.artcorner.common.root.ArtRoot.Child

@Composable
fun ArtRootContent(component: ArtRoot) {
    Children(routerState = component.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is Child.Main -> ArtMainContent(child.component)
        }
    }
}
