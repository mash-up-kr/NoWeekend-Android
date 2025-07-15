package team.noweekend.core.remote.api.recommend

import team.noweekend.core.remote.model.recommend.WeatherRecommendResponse

interface RecommendApi {
    suspend fun getWeatherRecommendVacation(): WeatherRecommendResponse
}
