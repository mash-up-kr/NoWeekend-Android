package team.noweekend.core.common.kotlin.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object ValidateUtils {
    fun isValidBirthday(input: String): Boolean {
        val regex = Regex("""^(19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$""")
        if (!regex.matches(input)) return false
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            LocalDate.parse(input, formatter)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }
}
