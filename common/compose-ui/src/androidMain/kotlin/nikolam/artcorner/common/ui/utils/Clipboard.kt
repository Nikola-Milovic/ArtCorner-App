package nikolam.artcorner.common.ui.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService

@Composable
actual fun copyToClipboard(label : String, text : String){
    val context = LocalContext.current

    val clipboard: ClipboardManager? =
        getSystemService(context, ClipboardManager::class.java)
    val clip = ClipData.newPlainText(label, text)
    clipboard?.setPrimaryClip(clip)
}