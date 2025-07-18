package team.noweekend.core.remote.model.schedule.response

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class DeleteScheduleResponse(
    val data: String,
)
