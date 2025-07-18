package team.noweekend.core.remote.api.recommend

import team.noweekend.core.remote.model.recommend.request.PostRecommendVacationRequest
import team.noweekend.core.remote.model.recommend.response.SandwichRecommendResponse
import team.noweekend.core.remote.model.recommend.response.TodoRecommendResponse
import team.noweekend.core.remote.model.recommend.response.WeatherRecommendResponse

interface RecommendApi {
    suspend fun getWeatherRecommendVacation(): WeatherRecommendResponse
    suspend fun getSandwichRecommendVacation(): SandwichRecommendResponse
    suspend fun getRecommendTodoTag(): TodoRecommendResponse
    suspend fun postRecommendVacation(requestBody: PostRecommendVacationRequest)

    companion object{
        private const val RECOMMEND_PATH = "/api/v1/recommend"
        const val RECOMMEND_TAG_PATH = "$RECOMMEND_PATH/todo/mixed"
    }
}
