package team.noweekend.feature.home.model

data class RecommendResultUiModel(
    val title: String,
    val content: String,
    val iconStyle: String,
) {
    companion object {
        val DUMMY_DATA = RecommendResultUiModel(
            title = "",
            content = "",
            iconStyle = "",
        )
    }
}
