package nikolam.artcorner.common.data

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.json.JSONObject

class GroupDataSource (private val client : HttpClient) {

    fun createGroup(): Single<String> {
        val data = JSONObject()
        data.put("creatorId", "1234")
        data.put("maxMembers", 5)

        return singleFromCoroutine {
            val response: HttpResponse = client.post("http://localhost:4000/groups") {
                body = data.toString()
            }

            return@singleFromCoroutine response.receive()
        }
    }

}