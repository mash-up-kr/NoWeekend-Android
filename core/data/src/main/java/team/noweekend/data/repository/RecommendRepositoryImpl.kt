package team.noweekend.data.repository

import team.noweekend.core.domain.repository.RecommendRepository
import team.noweekend.core.model.vacation.WeatherRecommendVacation
import team.noweekend.core.remote.api.recommend.RecommendApi
import team.noweekend.data.mapper.toDomain
import javax.inject.Inject

internal class RecommendRepositoryImpl @Inject constructor(
    private val recommendApi: RecommendApi,
) : RecommendRepository {
    override suspend fun getWeatherRecommendVacation(): List<WeatherRecommendVacation> {
        return recommendApi.getWeatherRecommendVacation().weatherRecommendations.map { it.toDomain() }
    }
}
