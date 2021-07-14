package nikolam.artcorner.common.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Logger

actual fun getLogger(): Logger = CommonLogger()

