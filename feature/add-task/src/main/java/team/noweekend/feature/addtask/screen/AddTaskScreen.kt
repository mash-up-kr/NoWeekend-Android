package team.noweekend.feature.addtask.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.addtask.model.AddTaskType

@Composable
fun AddTaskScreen(
    onBackClick: () -> Unit,
    onClickDetailInfo: () -> Unit,
    onClickInfoSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier,
        topBar = {
            NWKHeader(
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick,
                text = stringResource(NWKStringResource.AddTaskHeaderTitle),
                content = {
                    Text(
                        modifier = Modifier.clickable { onClickInfoSave() },
                        text = stringResource(NWKStringResource.InputTextSaveLabel),
                        style = NWKTheme.typography.heading6,
                        color = NWKTheme.color.Toast.toast500,
                    )
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = NWKTheme.spacing.space200),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AddTaskTypeContainer(
                modifier = Modifier.fillMaxWidth(),
            )
            AddTaskInputContainer(
                modifier = Modifier.padding(vertical = NWKTheme.spacing.space300),
            )
            DetailInfoContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickDetailInfo() },
            )
        }
    }
}

@Composable
private fun DetailInfoContainer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(vertical = NWKTheme.spacing.space100),
            text = stringResource(NWKStringResource.AddTaskDetailLabel),
            style = NWKTheme.typography.subTitle1,
            color = NWKTheme.color.Semantic.Text.neutral,
        )
        NWKIcon(
            resourceId = NWKDrawableResource.ChevronRight,
        )
    }
}

@Composable
private fun AddTaskInputContainer(modifier: Modifier = Modifier) {
    val textFieldState = rememberTextFieldState()
    NWKInputField(
        modifier = modifier,
        isSingLine = false,
        placeholder = stringResource(NWKStringResource.AddTaskInputHint),
        textFieldState = textFieldState,
        onKeyboardAction = { Log.d("AddTaskScreen", "Keyboard action") },
    )
}

@Composable
private fun AddTaskTypeContainer(modifier: Modifier = Modifier) {
    var selectedType by remember { mutableStateOf(AddTaskType.COMPANY) }

    Row(
        modifier = modifier
            .clip(NWKTheme.radius.borderRadius500)
            .background(NWKTheme.color.Semantic.Border.border01)
            .padding(NWKTheme.spacing.space50),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AddTaskType.entries.forEach { type ->
            Text(
                modifier = Modifier
                    .then(
                        if (selectedType == type) {
                            Modifier
                                .border(
                                    width = 1.dp,
                                    color = NWKTheme.color.TaskItem.company,
                                    shape = NWKTheme.radius.borderRadius400,
                                )
                                .clip(NWKTheme.radius.borderRadius400)
                        } else {
                            Modifier
                        },
                    )
                    .clickable { selectedType = type }
                    .padding(horizontal = 26.dp, vertical = 5.dp),
                text = type.tag,
                style = NWKTheme.typography.body1,
                color = if (selectedType == type) {
                    NWKTheme.color.TaskItem.company
                } else {
                    NWKTheme.color.Semantic.Text.body
                },
            )
        }
    }
}

@Composable
@Preview
private fun AddTaskScreenPreview() {
    AddTaskScreen(
        modifier = Modifier.fillMaxSize(),
        onClickDetailInfo = {},
        onClickInfoSave = {},
        onBackClick = {},
    )
}
