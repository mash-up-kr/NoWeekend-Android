package team.noweekend.core.remote.model.holiday

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponse(
    @SerialName("holidays")
    val holidays: List<HolidayResponseDto>,
)

@Serializable
data class HolidayResponseDto(
    @SerialName("date")
    val date: String,
    @SerialName("content")
    val holiday: String,
)
