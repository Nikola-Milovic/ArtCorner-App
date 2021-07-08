package nikolam.artcorner.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import nikolam.artcorner.common.details.ArtDetails

@Composable
fun ArtDetailsContent(component: ArtDetails) {
    val model by component.models.subscribeAsState()

    Column {
        TopAppBar(title = { Text(text = "Details") })

        Box(Modifier.weight(1F)) {
           Text(model.text)

            Button(onClick = {component.closeDetails()}, content = { Text("Close")})
        }
    }
}
