package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.ScheduleRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    suspend operator fun invoke(id: String) = scheduleRepository.deleteSchedule(id = id)
}
