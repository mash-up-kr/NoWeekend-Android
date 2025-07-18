package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.model.schedule.ScheduleCreateParam
import javax.inject.Inject

class EditScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    suspend operator fun invoke(param: ScheduleCreateParam, id: String): Result<Unit> {
        return runCatching {
            scheduleRepository.editSchedule(
                param = param,
                id = id,
            )
        }
    }
}
