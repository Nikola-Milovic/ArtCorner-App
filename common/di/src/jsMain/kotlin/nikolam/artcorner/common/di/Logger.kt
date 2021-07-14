package nikolam.artcorner.common.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Logger

//TODO NSLogger
actual fun getLogger(): Logger = CommonLogger()

