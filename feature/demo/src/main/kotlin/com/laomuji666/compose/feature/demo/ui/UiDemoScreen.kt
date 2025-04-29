package com.laomuji666.compose.feature.demo.ui

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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.view.DragListDemo
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableRowClick
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.res.R

@Composable
fun UiDemoScreen(
    viewModel: UiDemoScreenViewModel = hiltViewModel(),
    onDateClick:()->Unit,
    onNestedScrollConnectionScreenClick:()->Unit,
    onNestedScrollDispatcherScreenClick:()->Unit,
    onPainterScreenClick:()->Unit,
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showDragListDialog by rememberSaveable {
        mutableStateOf(false)
    }
    if(showDragListDialog){
        Dialog(onDismissRequest = {
            showDragListDialog = false
        }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            DragListDemo(
                dragList = uiState.dragList,
                onSwap = { a,b ->
                    viewModel.onAction(UiDemoScreenAction.SwapDragList(a,b))
                }
            )
        }
    }

    UiDemoScreenUi(
        onDateClick = onDateClick,
        onNestedScrollConnectionScreenClick = onNestedScrollConnectionScreenClick,
        onNestedScrollDispatcherScreenClick = onNestedScrollDispatcherScreenClick,
        onLongClickSortClick = {
            showDragListDialog = true
        },
        onPainterScreenClick = onPainterScreenClick
    )
}

@Composable
private fun UiDemoScreenUi(
    onDateClick:()->Unit,
    onNestedScrollConnectionScreenClick:()->Unit,
    onNestedScrollDispatcherScreenClick:()->Unit,
    onLongClickSortClick:()->Unit,
    onPainterScreenClick:()->Unit,
){
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        WeTableRowClick(
            title = stringResource(id = R.string.string_demo_screen_date),
            onClick = onDateClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableRowClick(
            title = stringResource(id = R.string.string_demo_screen_scroll_connect),
            onClick = onNestedScrollConnectionScreenClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableRowClick(
            title = stringResource(id = R.string.string_demo_screen_scroll_dispatcher),
            onClick = onNestedScrollDispatcherScreenClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableRowClick(
            title = stringResource(id = R.string.string_demo_screen_long_click_sort),
            onClick = onLongClickSortClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
        WeTableRowClick(
            title = stringResource(id = R.string.string_demo_screen_painter),
            onClick = onPainterScreenClick,
            weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
        )
    }
}

@Preview
@Composable
private fun PreviewUiDemoScreen() {
    QuicklyTheme {
        WeScaffold {
            UiDemoScreen(
                viewModel = UiDemoScreenViewModel(),
                onDateClick = {},
                onNestedScrollConnectionScreenClick = {},
                onNestedScrollDispatcherScreenClick = {},
                onPainterScreenClick = {}
            )
        }
    }
}