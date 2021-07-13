package nikolam.artcorner.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import nikolam.artcorner.common.create.ArtCreate

@Composable
expect fun TopicsSelection()

@Composable
fun ArtCreateContent(component: ArtCreate) {
    val model by component.models.subscribeAsState()
    Column() {
        TopAppBar(title = { Text(text = "Creating New Group") }, navigationIcon = {
            // navigation icon is use
            // for drawer icon.
            IconButton(onClick = { }) {
                // below line is use to
                // specify navigation icon.
                Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back Button")
            }
        }
        )

        Spacer(modifier = Modifier.size(40.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CreationOptions(Modifier.weight(0.8f))
            Button(onClick = {}, content = { Text("Create") })
        }
    }
}

@Composable
fun CreationOptions(modifier: Modifier) {
    val textState = remember { mutableStateOf(TextFieldValue("5")) }

    Column(modifier = modifier.fillMaxHeight()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Maximum number of users in this group?")
            Spacer(Modifier.size(20.dp))
            OutlinedTextField(
                value = textState.value,
                onValueChange = {  value ->
                    val new = value.text
                    if (!(new.length > 1 || new.any { !it.isDigit() })) textState.value = value },
                modifier = Modifier
                    .background(Color.Transparent)
                    .wrapContentWidth()
                    .width(25.dp)
            )
        }

       TopicsSelection()

    }

}
