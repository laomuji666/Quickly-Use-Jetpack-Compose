package com.laomuji666.compose.feature.language

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.AppLanguages
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableRadioRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar

@Composable
fun LanguageScreen(
    viewModel: LanguageScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LanguageScreenUi(
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
    LaunchedEffect(Unit) {
        viewModel.updateLanguageStatus()
    }
}

@Composable
private fun LanguageScreenUi(
    uiState: LanguageScreenUiState,
    onAction: (LanguageScreenAction) -> Unit,
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    WeScaffold(
        topBar = {
            WeTopNavigationBar(
                title = stringResource(id = com.laomuji666.compose.res.R.string.string_language_screen_title),
                onBackClick = onBackClick
            )
        }
    ) {
        LazyColumn {
            items(items = uiState.appLanguageList) {
                WeTableRadioRow(
                    title = it.getDisplayName(context),
                    checked = it == uiState.usingLanguage,
                    onClick = {
                        onAction(LanguageScreenAction.OnLanguageClick(it, context))
                    },
                    weTableRowOutlineType = WeTableRowOutlineType.PADDING_START
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLanguageScreenUi() {
    QuicklyTheme {
        var usingLanguage: AppLanguages by remember { mutableStateOf(AppLanguages.FlowSystem) }
        LanguageScreenUi(
            uiState = LanguageScreenUiState(usingLanguage = usingLanguage),
            onAction = {
                when (it) {
                    is LanguageScreenAction.OnLanguageClick -> usingLanguage = it.appLanguage
                }
            },
            onBackClick = {}
        )
    }
}