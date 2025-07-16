package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.model.schedule.Schedule
import javax.inject.Inject

class ChangeCompleteScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    suspend operator fun invoke(id: String, isComplete: Boolean): Schedule =
        scheduleRepository.changeCompleteSchedule(id = id, isComplete = isComplete)
}
