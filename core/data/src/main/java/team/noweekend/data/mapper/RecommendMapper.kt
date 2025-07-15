package team.noweekend.data.mapper

import team.noweekend.core.model.vacation.SandwichRecommendVacation
import team.noweekend.core.model.vacation.WeatherRecommendVacation
import team.noweekend.core.remote.model.recommend.SandwichRecommendResponse
import team.noweekend.core.remote.model.recommend.WeatherRecommendResponseDto

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
