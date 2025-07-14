package team.noweekend.core.model.calendar

enum class ImageType {
    NONE,
    FUTURE_SCHEDULE,
    BURN_OUT,
    REST,
    OVER_ZERO_UNDER_FIFTY_DEGREE,
    OVER_FIFTY_UNDER_SEVENTY_FIVE_DEGREE
    ;
}

fun getImageType(temperature: Int, isFuture: Boolean, hasRest: Boolean) : ImageType{
    return when{
        hasRest -> ImageType.REST
        isFuture -> ImageType.FUTURE_SCHEDULE
        temperature in 1..49 -> ImageType.OVER_ZERO_UNDER_FIFTY_DEGREE
        temperature in 50..74 -> ImageType.OVER_FIFTY_UNDER_SEVENTY_FIVE_DEGREE
        temperature >= 75 -> ImageType.BURN_OUT
        else -> ImageType.NONE
    }

}
