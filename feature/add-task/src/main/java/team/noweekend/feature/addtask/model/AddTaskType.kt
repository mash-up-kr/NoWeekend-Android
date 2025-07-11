package team.noweekend.feature.addtask.model

enum class AddTaskType(val tag: String) {
    COMPANY(tag = "회사"),
    PERSONAL(tag = "개인"),
    OTHER(tag = "기타"),
    VACATION(tag = "연차"),
    ;
}
