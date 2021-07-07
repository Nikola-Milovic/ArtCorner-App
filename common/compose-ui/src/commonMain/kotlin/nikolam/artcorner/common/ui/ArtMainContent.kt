package nikolam.artcorner.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import nikolam.artcorner.common.main.GroupItem
import nikolam.artcorner.common.main.ArtMain

@Composable
fun ArtMainContent(component: ArtMain) {
    val model by component.models.subscribeAsState()

    Column {
        TopAppBar(title = { Text(text = "Groups") })

        Box(Modifier.weight(1F)) {
            GroupList(
                items = model.items
            )
        }
    }
}

@Composable
private fun GroupList(
    items: List<GroupItem>
) {
    Box {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(items) {
                Item(
                    item = it
                )

                Divider()
            }
        }
    }
}

@Composable
private fun Item(
    item: GroupItem
) {
    Box(modifier = Modifier.padding(10.dp)) {
        Text(item.text)
    }
}