package nikolam.artcorner.common.ui.utils

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.BitmapPainter

@Composable
actual fun KMPIcon(
    id: String,
    modifier: Modifier,
    colorFilter: ColorFilter?,
    contentDescription: String
)  {
    when (id) {
         "clipboard" -> {
            Icon(Icons.Filled.AccountBox, modifier = modifier,contentDescription = contentDescription)
        }
    }
}


