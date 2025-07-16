package team.noweekend.core.remote.model.recommend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherRecommendResponse(
    @SerialName("weatherResponses")
    val weatherRecommendations: List<WeatherRecommendResponseDto>,
)

@Serializable
data class WeatherRecommendResponseDto(
    @SerialName("localDate")
    val date: String,
    @SerialName("recommendContent")
    val content: String,
)
