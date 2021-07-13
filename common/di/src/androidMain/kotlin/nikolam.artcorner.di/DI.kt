package nikolam.artcorner.di

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Logger

actual fun getLogger(): Logger = LogcatLogger()

