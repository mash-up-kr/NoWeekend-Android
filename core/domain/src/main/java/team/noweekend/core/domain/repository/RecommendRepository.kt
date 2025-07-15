package team.noweekend.core.domain.repository

import team.noweekend.core.model.vacation.WeatherRecommendVacation

interface RecommendRepository {
    suspend fun getWeatherRecommendVacation(): List<WeatherRecommendVacation>
}
