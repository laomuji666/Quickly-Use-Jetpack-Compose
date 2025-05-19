package com.laomuji888.compose.feature.main.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.widget.click.WeClick
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType
import com.laomuji888.compose.feature.main.MainScreenAction
import com.laomuji888.compose.res.R

@Composable
fun SettingsScreen(
    onAction: (MainScreenAction) -> Unit
) {
    SettingsScreenUi(
        onAction = onAction
    )
}

@Composable
fun SettingsScreenUi(
    onAction: (MainScreenAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        WeClick(
            title = stringResource(id = R.string.string_language_screen_title), onClick = {
                onAction(MainScreenAction.OnLanguageClick)
            }, weOutlineType = WeOutlineType.PaddingHorizontal
        )
    }

}

@Preview
@Composable
private fun PreviewSettingsScreenUi() {
    QuicklyTheme {
        SettingsScreenUi(
            onAction = {})
    }
}