package com.laomuji666.compose.feature.template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableInput
import com.laomuji666.compose.core.ui.we.widget.WeTopActionBar

@Composable
fun TemplateScreen(
    viewModel: TemplateScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onButtonClick: (String) -> Unit,
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TemplateScreenUi(
        uiState = uiState,
        onAction = {
            viewModel.onAction(
                action = it,
                onButtonClick = onButtonClick
            )
        },
        onBackClick = onBackClick
    )
}

@Composable
private fun TemplateScreenUi(
    uiState: TemplateScreenUiState,
    onAction: (TemplateScreenAction)->Unit,
    onBackClick: () -> Unit,
){
    WeScaffold(
        topBar = {
            WeTopActionBar(
                title = uiState.title,
                onBackClick = onBackClick
            )
        }
    ) {
        WeTableInput(
            title = "",
            value = uiState.text,
            onValueChange = {
                onAction(TemplateScreenAction.OnTextChanged(it))
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            WeButton(
                text = "",
                onClick = {
                    onAction(TemplateScreenAction.OnButtonClicked)
                },
                weButtonType = WeButtonType.BIG
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTemplateScreenUi() {
    QuicklyTheme {
        TemplateScreenUi(
            uiState = TemplateScreenUiState(
                title = "Title",
                text = "Text",
            ),
            onAction = {},
            onBackClick = {}
        )
    }
}