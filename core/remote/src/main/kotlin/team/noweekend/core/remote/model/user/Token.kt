package team.noweekend.core.remote.model.user

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
