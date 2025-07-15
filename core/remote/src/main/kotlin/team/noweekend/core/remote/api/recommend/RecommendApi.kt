package team.noweekend.core.remote.api.recommend

import team.noweekend.core.remote.model.recommend.SandwichRecommendResponse
import team.noweekend.core.remote.model.recommend.WeatherRecommendResponse

interface RecommendApi {
    suspend fun getWeatherRecommendVacation(): WeatherRecommendResponse
    suspend fun getSandwichRecommendVacation(): SandwichRecommendResponse
}
