package team.noweekend.feature.profile.settingAccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.profile.ProfileInputComponent
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.component.topbar.SettingAccountTopBar

@Composable
fun SettingAccountScreen(
    onClickSaveButton: () -> Unit,
    onclickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
    nickName: TextFieldState = rememberTextFieldState(),
    birth: TextFieldState = rememberTextFieldState(),

    ) {


    val nickNameInputFieldStatus by remember {
        derivedStateOf {
            if (nickName.text.length <= 6) InputFieldStatus.DEFAULT
            else InputFieldStatus.ERROR
        }
    }

    val birthInputFieldStatus by remember {
        derivedStateOf {
            if (birth.text.any { it.isDigit().not() }) {
                InputFieldStatus.ERROR
            } else {
                InputFieldStatus.DEFAULT
            }
        }
    }

    Column(
        modifier = modifier,
    ) {
        SettingAccountTopBar(
            onClickSaveButton = onClickSaveButton,
            onClickBackButton = onclickBackButton,
        )
        Spacer(modifier = Modifier.height(48.dp))
        ProfileInputComponent(
            nickName = nickName,
            birth = birth,
            nickNameInputFieldStatus = nickNameInputFieldStatus,
            birthInputFieldStatus = birthInputFieldStatus,
        )
    }
}


@Preview
@Composable
private fun PreviewSettingAccountScreen() {
    NWKTheme {
        SettingAccountScreen(
            modifier = Modifier.fillMaxSize(),
            onClickSaveButton = {},
            onclickBackButton = {}
        )
    }
}
