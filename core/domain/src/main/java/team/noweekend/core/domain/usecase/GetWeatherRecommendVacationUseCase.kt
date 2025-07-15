package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.RecommendRepository
import team.noweekend.core.model.vacation.WeatherRecommendVacation
import javax.inject.Inject

class GetWeatherRecommendVacationUseCase @Inject constructor(
    private val repository: RecommendRepository,
) {
    suspend operator fun invoke(): Result<List<WeatherRecommendVacation>> = runCatching {
        repository.getWeatherRecommendVacation()
    }
}
