package team.noweekend.core.remote.api.holiday

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.model.holiday.HolidayResponse
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class HolidayApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : HolidayApi {

    override suspend fun getHoliday(year: Int, month: Int): HolidayResponse {
        return client.getApiCall(
            path = "/api/v1/holiday",
            queries = mapOf(
                "year" to year,
                "month" to month,
            ),
        )
    }

    override suspend fun getRemainedHoliday(): HolidayResponse {
        return client.getApiCall(
            path = "/api/v1/holiday/remaining",
        )
    }
}
