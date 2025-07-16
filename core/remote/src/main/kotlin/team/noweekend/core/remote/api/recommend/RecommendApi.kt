package team.noweekend.core.remote.api.recommend

import team.noweekend.core.remote.model.recommend.SandwichRecommendResponse
import team.noweekend.core.remote.model.recommend.TodoRecommendResponse
import team.noweekend.core.remote.model.recommend.WeatherRecommendResponse

interface RecommendApi {
    suspend fun getWeatherRecommendVacation(): WeatherRecommendResponse
    suspend fun getSandwichRecommendVacation(): SandwichRecommendResponse
    suspend fun getRecommendTodoTag(): TodoRecommendResponse

    companion object{
        private const val RECOMMEND_PATH = "/api/v1/recommend"
        const val RECOMMEND_TAG_PATH = "$RECOMMEND_PATH/todo/mixed"
    }
}
