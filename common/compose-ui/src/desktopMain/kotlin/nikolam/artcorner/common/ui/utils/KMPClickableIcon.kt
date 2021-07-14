package nikolam.artcorner.common.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter

@Composable
actual fun KMPClickableIcon(
    id: String,
    modifier: Modifier,
    colorFilter: ColorFilter?,
    contentDescription: String,
    onClick : ()-> Unit
) {}