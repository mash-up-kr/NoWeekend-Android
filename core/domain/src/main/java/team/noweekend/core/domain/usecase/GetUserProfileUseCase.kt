package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.UserRepository
import team.noweekend.core.model.user.User
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(): Result<User> = runCatching {
        repository.getUserProfile()
    }
}
