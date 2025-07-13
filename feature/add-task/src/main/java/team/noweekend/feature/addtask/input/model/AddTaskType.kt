package team.noweekend.feature.addtask.input.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

enum class AddTaskType(val tag: String) {
    COMPANY(tag = "회사"),
    PERSONAL(tag = "개인"),
    OTHER(tag = "기타"),
    VACATION(tag = "연차"),
    ;

    companion object {
        fun getDetailTitleList(): ImmutableList<String> {
            return listOf(
                "하루 종일",
                "시작",
                "종료",
                "알림",
                "알림 온도"
            ).toImmutableList()
        }
    }
}
