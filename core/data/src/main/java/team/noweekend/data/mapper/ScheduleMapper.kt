package team.noweekend.data.mapper

import team.noweekend.core.model.alarm.AlarmOption
import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.remote.model.schedule.common.ScheduleModel
import team.noweekend.core.remote.model.schedule.response.GetScheduleResponse

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
