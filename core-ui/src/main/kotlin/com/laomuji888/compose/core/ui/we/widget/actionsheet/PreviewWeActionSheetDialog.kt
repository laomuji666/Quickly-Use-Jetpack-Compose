package com.laomuji888.compose.core.ui.we.widget.actionsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji888.compose.core.ui.theme.QuicklyTheme
import com.laomuji888.compose.core.ui.we.widget.outline.WeOutlineType

@PreviewLightDark
@Composable
fun PreviewWeActionSheetDialog() {
    var showDialog by remember {
        mutableStateOf(true)
    }
    QuicklyTheme {
        if (showDialog) {
            WeActionSheetDialog(
                onDismissRequest = { showDialog = false }) {
                WeActionSheet(
                    text = "警示操作提示文案",
                    weOutlineType = WeOutlineType.Full,
                    weActionSheetType = WeActionSheetType.Summary
                )
                WeActionSheet(
                    "操作一",
                    weOutlineType = WeOutlineType.Full,
                )
                WeActionSheet(
                    "操作二",
                    weOutlineType = WeOutlineType.Full,
                )
                WeActionSheet(
                    "操作三",
                    weOutlineType = WeOutlineType.Full,
                )
                WeActionSheet(
                    text = "警示操作",
                    weActionSheetType = WeActionSheetType.Wrong,
                    weOutlineType = WeOutlineType.Split,
                )
                WeActionSheet(
                    text = "取消"
                )
            }
        }
    }
}