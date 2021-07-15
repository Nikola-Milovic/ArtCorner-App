package nikolam.artcorner.common.ui.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService

@Composable
actual fun copyToClipboard(label: String, text: String): () -> Unit {
    val context = LocalContext.current

    return { context.copyToClipboard(label, text) }
}

private fun Context.copyToClipboard(label: String, text: String) {
    val clipboard: ClipboardManager? =
        getSystemService(this, ClipboardManager::class.java)
    val clip = ClipData.newPlainText(label, text)
    clipboard?.setPrimaryClip(clip)
}