package nikolam.artcorner.common.ui.utils

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter

@Composable
actual fun KMPClickableIcon(
    id: String,
    modifier: Modifier,
    colorFilter: ColorFilter?,
    contentDescription: String,
    onClick: () -> Unit
) {
    when (id) {
        "clipboard" -> {
            IconButton(content = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = contentDescription
                )
            }, modifier = modifier, onClick = { onClick() })
        }
    }
}
