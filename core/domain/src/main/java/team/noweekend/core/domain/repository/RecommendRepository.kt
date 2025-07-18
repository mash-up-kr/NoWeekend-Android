package team.noweekend.core.domain.repository

import team.noweekend.core.model.vacation.RecommendVacationResult
import team.noweekend.core.model.vacation.SandwichRecommendVacation
import team.noweekend.core.model.vacation.WeatherRecommendVacation

interface RecommendRepository {
    suspend fun getWeatherRecommendVacation(): List<WeatherRecommendVacation>
    suspend fun getSandwichRecommendVacation(): SandwichRecommendVacation
    suspend fun getRecommendTodoTag(): List<String>
    suspend fun postRecommendVacation(
        days: Int,
        travelStyle: String,
        activityType: String,
        restPreference: String,
        leisurePreference: String,
    )

    suspend fun getRecommendVacation(): RecommendVacationResult
}
