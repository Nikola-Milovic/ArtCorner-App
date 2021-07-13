package nikolam.artcorner.di.preference

import com.russhwolf.settings.Settings

class PreferenceManager(settings: Settings): Settings by settings {

    companion object {
        const val USER_ID = "userid"
        const val FIRST_LAUNCH = "firstLaunch"
    }

    /* TO CHECK IF THIS IS APP's FIRST LAUNCH */
    val getUserID get() = getStringOrNull(USER_ID)
    fun setUserID(id : String) = putString(USER_ID, id)

    /* TO CHECK IF THIS IS APP's FIRST LAUNCH */
    val isFirstLaunch get() = getBooleanOrNull(FIRST_LAUNCH) ?: true
    fun firstLaunchDone() = putBoolean(FIRST_LAUNCH, false)
}