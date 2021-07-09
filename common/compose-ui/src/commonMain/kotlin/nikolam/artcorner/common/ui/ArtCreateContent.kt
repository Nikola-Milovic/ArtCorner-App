package nikolam.artcorner.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import nikolam.artcorner.common.create.ArtCreate

@Composable
fun ArtCreateContent(component: ArtCreate) {
    val model by component.models.subscribeAsState()

    Column {
        TopAppBar(title = { Text(text = "Creating New Group") })


        Text(model.text)

        Button(onClick = { component.closeCreation() }, content = { Text("Close") })
    }
}
