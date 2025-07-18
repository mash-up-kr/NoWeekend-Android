package team.noweekend.data.mapper

import team.noweekend.core.model.vacation.SandwichRecommendVacation
import team.noweekend.core.model.vacation.WeatherRecommendVacation
import team.noweekend.core.remote.model.recommend.response.SandwichRecommendResponse
import team.noweekend.core.remote.model.recommend.response.WeatherRecommendResponseDto

internal fun WeatherRecommendResponseDto.toDomain(): WeatherRecommendVacation =
    WeatherRecommendVacation(
        date = date,
        content = content,
    )

internal fun SandwichRecommendResponse.toDomain(): SandwichRecommendVacation =
    SandwichRecommendVacation(
        startDate = startDate,
        endDate = endDate,
    )
