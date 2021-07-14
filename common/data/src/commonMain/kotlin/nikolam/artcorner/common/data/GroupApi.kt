package nikolam.artcorner.common.data

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.json.JSONObject

class GroupApi (private val client : HttpClient) {
    fun createGroup(): Single<CreateGroupResponse> {
        val data = JSONObject()
        data.put("creatorId", "1234")
        data.put("maxMembers", 5)

        return singleFromCoroutine {
            val response: HttpResponse = client.post("http://10.0.2.2:4000/local/groups") {
                body = data.toString()
            }

            val decoded : CreateGroupResponse = response.receive()

            decoded.status = response.status.value

            return@singleFromCoroutine decoded
        }
    }

}