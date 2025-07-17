package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.model.schedule.CreateSchedule
import team.noweekend.core.model.schedule.ScheduleCreateParam
import javax.inject.Inject

class CreateAddTaskUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    suspend operator fun invoke(param: ScheduleCreateParam): Result<CreateSchedule> {
        return runCatching { scheduleRepository.createSchedule(param) }
    }
}
