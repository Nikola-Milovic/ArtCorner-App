package nikolam.artcorner.common.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.main.GroupItem
import nikolam.artcorner.common.ui.create.ArtCreateContent
import nikolam.artcorner.common.ui.details.ArtDetailsContent

@Composable
fun ArtMainContent(component: ArtMain) {

    Children(routerState = component.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is ArtMain.Child.Nothing -> HomeView(component)
            is ArtMain.Child.Create -> ArtCreateContent(child.component)
            is ArtMain.Child.Details -> ArtDetailsContent(child.component)
        }
    }

}

@Composable
expect fun Tabs()

@Composable
private fun HomeView(component: ArtMain) {
    val model by component.models.subscribeAsState()

    Column {
        //  Tabs()

        Box(Modifier.weight(1F)) {
            GroupList(
                items = model.items,
                component::navigateToDetailsGroup
            )
        }

        Button(onClick = { component.navigateToCreateGroup() }, content = { Text("NewGroup") })
    }
}

@Composable
private fun GroupList(
    items: List<GroupItem>,
    onItemClicked: (gid: String) -> Unit
) {
    Box {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(items) {
                Item(
                    item = it,
                    onItemClicked
                )

                Divider()
            }
        }
    }
}

@Composable
private fun Item(
    item: GroupItem,
    onItemClicked: (gid: String) -> Unit
) {
    Box(modifier = Modifier.padding(10.dp).clickable { onItemClicked(item.gid) }) {
        Text(item.ownerId)
    }
}