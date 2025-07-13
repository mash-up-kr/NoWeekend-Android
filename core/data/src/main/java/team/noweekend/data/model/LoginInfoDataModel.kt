package team.noweekend.data.model

import team.noweekend.core.model.login.LoginInfoDomainModel
import team.noweekend.core.remote.model.LoginResponse

data class LoginInfoDataModel(
    val exists: Boolean,
    val accessToken: String,
) {
    fun toDomainModel(): LoginInfoDomainModel {
        return LoginInfoDomainModel(
            exists = exists,
            accessToken = accessToken,
        )
    }
}

fun LoginResponse.toDataModel(): LoginInfoDataModel {
    return LoginInfoDataModel(
        exists = exists,
        accessToken = accessToken,
    )
}
