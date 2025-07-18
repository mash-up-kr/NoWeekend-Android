package team.noweekend.core.remote.model.recommend.request

import kotlinx.serialization.Serializable

@Serializable
data class PostRecommendVacationRequest(
    val days: Int,
    val travelStyle: String,
    val activityType: String,
    val restPreference: String,
    val leisurePreference: String,
)
