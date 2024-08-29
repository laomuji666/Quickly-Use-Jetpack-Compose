package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeActionSheetDialog
import com.laomuji666.compose.core.ui.we.widget.WeActionSheetRow
import com.laomuji666.compose.core.ui.we.widget.WeActionSheetType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableCheckRow
import com.laomuji666.compose.core.ui.we.widget.WeTableClickRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRadioRow
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeTableSwitchRow
import com.laomuji666.compose.core.ui.we.widget.WeToast
import com.laomuji666.compose.core.ui.we.widget.WeToastType
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar
import com.laomuji666.compose.res.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WidgetScreen(){
    WidgetScreenUi(

    )
}

@Composable
private fun WidgetScreenUi(){
    WeScaffold(
        topBar = {
            WeTopNavigationBar(title = stringResource(id = R.string.string_hello_screen_navigation_widget))
        }
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ToastWidget()
            ActionSheetWidget()
        }
    }
}

@Composable
private fun ToastWidget(){
    val coroutineScope = rememberCoroutineScope()
    var isEnable by rememberSaveable { mutableStateOf(false) }
    val titleList = listOf(stringResource(id = R.string.string_toast_loading), stringResource(id = R.string.string_toast_done), stringResource(id = R.string.string_toast_error))
    var currentItem by rememberSaveable { mutableIntStateOf(0) }

    var showLoadingDialog by rememberSaveable { mutableStateOf(false) }
    if(showLoadingDialog){
        WeToast(weToastType = WeToastType.LOADING, message = titleList[0])
    }
    var showDoneDialog by rememberSaveable { mutableStateOf(false) }
    if(showDoneDialog){
        WeToast(weToastType = WeToastType.Done, message = titleList[1])
    }
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    if(showErrorDialog){
        WeToast(weToastType = WeToastType.ERROR, message = titleList[2])
    }
    WeTableSwitchRow(
        title = stringResource(id = R.string.string_widget_screen_toast_title),
        checked = isEnable,
        onClick = {
            isEnable = !isEnable
        },
        weTableRowOutlineType = if(isEnable) WeTableRowOutlineType.FULL else WeTableRowOutlineType.SPLIT
    )
    if(!isEnable){
        return
    }

    Column {
        titleList.forEachIndexed { index, title ->
            WeTableRadioRow(
                title = title,
                checked = currentItem == index,
                weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
                onClick = { currentItem = index }
            )
        }
    }
    WeTableClickRow(
        title = stringResource(id = R.string.string_widget_screen_show_toast),
        weTableRowOutlineType = WeTableRowOutlineType.SPLIT,
        onClick = {
            if(currentItem == 0){
                showLoadingDialog = true
                delayRun(coroutineScope){
                    showLoadingDialog = false
                }
            }
            if(currentItem == 1){
                showDoneDialog = true
                delayRun(coroutineScope){
                    showDoneDialog = false
                }
            }
            if(currentItem == 2) {
                showErrorDialog = true
                delayRun(coroutineScope) {
                    showErrorDialog = false
                }
            }
        })

}

private fun delayRun(coroutineScope: CoroutineScope, delay:Long = 2000, block:()->Unit){
    coroutineScope.launch {
        delay(delay)
        block()
    }
}

@Composable
private fun ActionSheetWidget(){
    var isEnable by rememberSaveable { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    WeTableSwitchRow(
        title = stringResource(id = R.string.string_widget_screen_action_sheet_title),
        checked = isEnable,
        onClick = {
            isEnable = !isEnable
        },
        weTableRowOutlineType = if(isEnable) WeTableRowOutlineType.FULL else WeTableRowOutlineType.SPLIT
    )
    if(!isEnable){
        return
    }

    var checked1 by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(true) }
    var checked3 by remember { mutableStateOf(false) }
    var checked4 by remember { mutableStateOf(true) }
    WeTableCheckRow(
        title = stringResource(id = R.string.string_widget_screen_action_sheet_item1),
        checked = checked1,
        onClick = { checked1 = !checked1 },
        weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL
    )
    WeTableCheckRow(
        title = stringResource(id = R.string.string_widget_screen_action_sheet_item2),
        checked = checked2,
        onClick = { checked2 = !checked2 },
        weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
    )
    WeTableCheckRow(
        title = stringResource(id = R.string.string_widget_screen_action_sheet_item3),
        checked = checked3,
        onClick = { checked3 = !checked3 },
        weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
    )
    WeTableCheckRow(
        title = stringResource(id = R.string.string_widget_screen_action_sheet_item4),
        checked = checked4,
        onClick = { checked4 = !checked4 },
        weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
    )

    WeTableClickRow(title = stringResource(id = R.string.string_widget_screen_show_action_sheet), onClick = {
        showDialog = true
    })

    if(showDialog){
        WeActionSheetDialog(
            onDismissRequest = { showDialog = false },
            dismissText = stringResource(id = R.string.string_widget_screen_action_sheet_cancel)
        ){
            if(checked1){
                WeActionSheetRow(
                    text = stringResource(id = R.string.string_widget_screen_action_sheet_item1),
                    weTableRowOutlineType = WeTableRowOutlineType.FULL,
                    weActionSheetType = WeActionSheetType.SUMMARY
                )
            }
            if(checked2){
                WeActionSheetRow(
                    stringResource(id = R.string.string_widget_screen_action_sheet_item2),
                    weTableRowOutlineType = WeTableRowOutlineType.FULL
                )
            }
            if(checked3){
                WeActionSheetRow(
                    stringResource(id = R.string.string_widget_screen_action_sheet_item3),
                    weTableRowOutlineType = WeTableRowOutlineType.FULL
                )
            }
            if(checked4){
                WeActionSheetRow(
                    text = stringResource(id = R.string.string_widget_screen_action_sheet_item4),
                    weActionSheetType = WeActionSheetType.WRONG,
                    weTableRowOutlineType = WeTableRowOutlineType.SPLIT
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewHttpScreen(){
    QuicklyTheme {
        WidgetScreenUi()
    }
}

