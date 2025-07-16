package team.noweekend.data.repository

import team.noweekend.core.domain.repository.RecommendRepository
import team.noweekend.core.model.vacation.SandwichRecommendVacation
import team.noweekend.core.model.vacation.WeatherRecommendVacation
import team.noweekend.core.remote.api.recommend.RecommendApi
import team.noweekend.core.remote.model.recommend.TodoRecommendResponse.Companion.toTagList
import team.noweekend.data.mapper.toDomain
import javax.inject.Inject

internal class RecommendRepositoryImpl @Inject constructor(
    private val recommendApi: RecommendApi,
) : RecommendRepository {
    override suspend fun getWeatherRecommendVacation(): List<WeatherRecommendVacation> {
        return recommendApi.getWeatherRecommendVacation().weatherRecommendations.map { it.toDomain() }
    }

    override suspend fun getSandwichRecommendVacation(): SandwichRecommendVacation {
        return recommendApi.getSandwichRecommendVacation().toDomain()
    }

    override suspend fun getRecommendTodoTag(): List<String> {
        val response = recommendApi.getRecommendTodoTag()
        return response.toTagList()
    }
}
