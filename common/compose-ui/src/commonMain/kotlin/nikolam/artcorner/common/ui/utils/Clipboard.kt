package nikolam.artcorner.common.ui.utils

import androidx.compose.runtime.Composable

@Composable
expect fun copyToClipboard(label : String, text : String) : () -> Unit