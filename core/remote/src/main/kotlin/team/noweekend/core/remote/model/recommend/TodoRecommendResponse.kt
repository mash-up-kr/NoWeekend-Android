package team.noweekend.core.remote.model.recommend

import kotlinx.serialization.Serializable

@Serializable
data class TodoRecommendResponse(
    val firstRecommendTag: RecommendTag,
    val secondRecommendTag: RecommendTag,
    val thirdRecommendTag: RecommendTag,
) {
    companion object {
        fun TodoRecommendResponse.toTagList(): List<String> {
            return listOf(
                firstRecommendTag.content,
                secondRecommendTag.content,
                thirdRecommendTag.content,
            )
        }
    }
}

@Serializable
data class RecommendTag(
    val content: String,
)
