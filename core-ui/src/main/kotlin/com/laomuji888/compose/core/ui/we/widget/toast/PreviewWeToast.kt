package com.laomuji888.compose.core.ui.we.widget.toast

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.laomuji888.compose.core.ui.theme.QuicklyTheme

@Preview
@Composable
fun PreviewWeToast1() {
    QuicklyTheme {
        WeToast(WeToastType.Done, "已发送")
    }
}

@Preview
@Composable
fun PreviewWeToast2() {
    QuicklyTheme {
        WeToast(WeToastType.Loading, "加载中")
    }
}

@Preview
@Composable
fun PreviewWeToast3() {
    QuicklyTheme {
        WeToast(WeToastType.Error, "获取链接失败")
    }
}