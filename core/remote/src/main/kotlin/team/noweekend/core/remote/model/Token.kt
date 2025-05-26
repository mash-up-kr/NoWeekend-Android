package team.noweekend.core.remote.model

data class Token(
    val access: String,
    val refresh: String,
) {
    companion object {
        val INITIAL_STATE: Token = Token(
            access = "",
            refresh = ""
        )
    }
}