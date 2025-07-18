package team.noweekend.core.remote.api.recommend

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.base.postApiCall
import team.noweekend.core.remote.model.recommend.request.PostRecommendVacationRequest
import team.noweekend.core.remote.model.recommend.response.RecommendVacationResultResponse
import team.noweekend.core.remote.model.recommend.response.SandwichRecommendResponse
import team.noweekend.core.remote.model.recommend.response.TodoRecommendResponse
import team.noweekend.core.remote.model.recommend.response.WeatherRecommendResponse
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class RecommendApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : RecommendApi {
    override suspend fun getWeatherRecommendVacation(): WeatherRecommendResponse {
        return client.getApiCall(path = "/api/v1/recommend/weather")
    }

    override suspend fun getSandwichRecommendVacation(): SandwichRecommendResponse {
        return client.getApiCall(path = "/api/v1/recommend/sandwich")
    }

    override suspend fun getRecommendTodoTag(): TodoRecommendResponse {
        return client.getApiCall(path = RecommendApi.RECOMMEND_TAG_PATH)
    }

    override suspend fun postRecommendVacation(
        requestBody: PostRecommendVacationRequest,
    ) {
        client.postApiCall<String>(
            path = "/api/v1/recommend/vacation",
            body = requestBody,
        )
    }

    override suspend fun getRecommendVacation(): RecommendVacationResultResponse {
        return client.getApiCall<RecommendVacationResultResponse>(
            path = "/api/v1/recommend/vacation",
        )
    }
}
