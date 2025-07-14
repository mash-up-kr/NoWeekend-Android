package team.noweekend.data.model

import team.noweekend.core.model.alarm.AlarmOption
import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.remote.model.schedule.response.GetScheduleResponse

fun GetScheduleResponse.toDomain() : DateWithSchedules {
    return DateWithSchedules(
        date = this.date,
        dailyTemperature =  this.dailyTemperature,
        schedules = this.schedules.map{ scheduleModel->
            Schedule(
                id = scheduleModel.id,
                title = scheduleModel.title,
                startTime = scheduleModel.startTime,
                endTime = scheduleModel.endTime,
                category = ScheduleCategory.valueOf(scheduleModel.category),
                temperature = scheduleModel.temperature,
                allDay = scheduleModel.allDay,
                completed = scheduleModel.completed,
                alarmOption = AlarmOption.valueOf(scheduleModel.alarmOption)
            )
        }
    )

}
