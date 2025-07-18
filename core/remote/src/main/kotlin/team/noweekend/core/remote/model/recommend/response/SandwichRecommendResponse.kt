package team.noweekend.core.remote.model.recommend.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SandwichRecommendResponse(
    @SerialName("startDate")
    val startDate: String,
    @SerialName("endDate")
    val endDate: String,
)
