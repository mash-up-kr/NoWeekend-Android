package team.noweekend.core.domain.usecase

import kotlinx.datetime.LocalDate
import team.noweekend.core.domain.repository.HolidayRepository
import team.noweekend.core.model.holiday.Holiday
import javax.inject.Inject

class GetHolidayUseCase @Inject constructor(
    private val holidayRepository: HolidayRepository,
) {
    suspend operator fun invoke(date: LocalDate): Result<List<Holiday>> = runCatching {
        holidayRepository.getHoliday(year = date.year, month = date.monthNumber)
    }

    suspend fun getRemainedHoliday(): Result<List<Holiday>> = runCatching {
        holidayRepository.getRemainedHoliday()
    }
}
