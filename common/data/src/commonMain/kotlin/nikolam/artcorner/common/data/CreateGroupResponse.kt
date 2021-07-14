package nikolam.artcorner.common.data

import kotlinx.serialization.Serializable

@Serializable
data class CreateGroupResponse (var status : Int = -1, val gid : String)