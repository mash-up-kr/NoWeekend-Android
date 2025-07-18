package team.noweekend.core.remote.model.recommend.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendVacationResultResponse(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("iconStyle")
    val iconStyle: String,
)
