package team.noweekend.data.mapper

import team.noweekend.core.model.alarm.AlarmOption
import team.noweekend.core.model.schedule.CreateSchedule
import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.model.schedule.ScheduleCreateParam
import team.noweekend.core.remote.model.schedule.ScheduleCreateRequest
import team.noweekend.core.remote.model.schedule.common.ScheduleModel
import team.noweekend.core.remote.model.schedule.response.EditScheduleRequest
import team.noweekend.core.remote.model.schedule.response.EditScheduleResponse
import team.noweekend.core.remote.model.schedule.response.GetScheduleResponse
import team.noweekend.core.remote.model.schedule.response.ScheduleCreateResponse

fun GetScheduleResponse.toDomain(): DateWithSchedules {
    return DateWithSchedules(
        date = this.date,
        dailyTemperature = this.dailyTemperature,
        schedules = this.schedules.map { scheduleModel ->
            scheduleModel.toSchedule()
        },
    )
}

fun ScheduleModel.toSchedule(): Schedule {
    return Schedule(
        id = this.id,
        title = this.title,
        startTime = this.startTime,
        endTime = this.endTime,
        category = ScheduleCategory.valueOf(this.category),
        temperature = this.temperature,
        allDay = this.allDay,
        completed = this.completed,
        alarmOption = AlarmOption.valueOf(this.alarmOption),
    )
}

fun ScheduleCreateParam.toRequest(): ScheduleCreateRequest {
    return ScheduleCreateRequest(
        title = this.title,
        startDateTime = this.startDateTime,
        endDateTime = this.endDateTime,
        category = this.category,
        temperature = this.temperature,
        alarmOption = this.alarmOption,
    )
}

fun ScheduleCreateResponse.toDomainModel(): CreateSchedule {
    return CreateSchedule(
        alarmOption = this.alarmOption,
        category = this.category,
        completed = this.completed,
        startDateTime = this.startDateTime,
        id = this.id,
        endDateTime = this.endDateTime,
        temperature = this.temperature,
        title = this.title,
    )
}

fun EditScheduleResponse.toDomainModel(): CreateSchedule {
    return CreateSchedule(
        alarmOption = this.alarmOption,
        category = this.category,
        completed = this.completed,
        startDateTime = this.startDateTime,
        id = this.id,
        endDateTime = this.endDateTime,
        temperature = this.temperature,
        title = this.title,
    )
}

fun ScheduleCreateParam.toEditRequest(): EditScheduleRequest {
    return EditScheduleRequest(
        title = this.title,
        startDateTime = this.startDateTime,
        endDateTime = this.endDateTime,
        category = this.category,
        temperature = this.temperature,
        alarmOption = this.alarmOption,
    )
}
