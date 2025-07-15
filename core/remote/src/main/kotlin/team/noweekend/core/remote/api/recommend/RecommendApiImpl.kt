package team.noweekend.core.remote.api.recommend

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.model.recommend.WeatherRecommendResponse
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class RecommendApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : RecommendApi {
    override suspend fun getWeatherRecommendVacation(): WeatherRecommendResponse {
        return client.getApiCall(path = "/api/v1/recommend/weather")
    }
}
