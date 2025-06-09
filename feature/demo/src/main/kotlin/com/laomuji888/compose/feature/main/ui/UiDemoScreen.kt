package com.laomuji888.compose.feature.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.view.DragListDemo
import com.laomuji888.compose.core.ui.view.banner.PreviewBanner
import com.laomuji888.compose.core.ui.we.WeDialog
import com.laomuji888.compose.core.ui.we.widget.click.WeClick
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutline
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType
import com.laomuji888.compose.core.ui.we.widget.scaffold.WeScaffold
import com.laomuji888.compose.feature.main.MainScreenAction
import com.laomuji888.compose.res.R

@Composable
fun UiDemoScreen(
    viewModel: UiDemoScreenViewModel = hiltViewModel(),
    onAction: (MainScreenAction) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showDragListDialog by rememberSaveable {
        mutableStateOf(false)
    }
    if (showDragListDialog) {
        WeDialog(
            onDismissRequest = {
                showDragListDialog = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            DragListDemo(
                dragList = uiState.dragList,
                onSwap = { a, b ->
                    viewModel.onAction(UiDemoScreenAction.SwapDragList(a, b))
                },
            )
        }
    }

    UiDemoScreenUi(
        onLongClickSortClick = {
            showDragListDialog = true
        },
        onAction = onAction,
    )
}

@Composable
private fun UiDemoScreenUi(
    onLongClickSortClick: () -> Unit,
    onAction: (MainScreenAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PreviewBanner()
        WeOutline(weOutlineType = WeOutlineType.Split)
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_long_click_sort),
            onClick = onLongClickSortClick,
            weOutlineType = WeOutlineType.PaddingHorizontal,
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_date),
            onClick = {
                onAction(MainScreenAction.OnDateClick)
            },
            weOutlineType = WeOutlineType.PaddingHorizontal,
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_scroll_connect),
            onClick = {
                onAction(MainScreenAction.OnNestedScrollConnectionScreenClick)
            },
            weOutlineType = WeOutlineType.PaddingHorizontal,
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_scroll_dispatcher),
            onClick = {
                onAction(MainScreenAction.OnNestedScrollDispatcherScreenClick)
            },
            weOutlineType = WeOutlineType.PaddingHorizontal,
        )
        WeClick(
            title = stringResource(id = R.string.string_demo_screen_painter),
            onClick = {
                onAction(MainScreenAction.OnPainterScreenClick)
            },
            weOutlineType = WeOutlineType.PaddingHorizontal,
        )
    }
}

@Preview
@Composable
private fun PreviewUiDemoScreen() {
    QuicklyTheme {
        WeScaffold {
            UiDemoScreenUi(onLongClickSortClick = {}, onAction = {})
        }
    }
}