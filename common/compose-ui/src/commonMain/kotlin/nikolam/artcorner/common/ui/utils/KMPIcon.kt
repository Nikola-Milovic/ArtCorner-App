package nikolam.artcorner.common.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.BitmapPainter

@Composable
expect fun KMPIcon(
    id: String,
    modifier: Modifier,
    colorFilter: ColorFilter?,
    contentDescription: String
)
