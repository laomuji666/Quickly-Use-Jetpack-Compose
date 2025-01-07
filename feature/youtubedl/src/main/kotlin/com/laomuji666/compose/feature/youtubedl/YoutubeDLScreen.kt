package com.laomuji666.compose.feature.youtubedl

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableInput
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutline
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeToast
import com.laomuji666.compose.core.ui.we.widget.WeToastType
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBar

@Composable
fun YoutubeDLScreen(
    viewModel: YoutubeDLScreenViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    YoutubeDLScreenUi(
        uiState = uiState,
        onAction = viewModel::onAction
    )

    if(uiState.isLoading){
        WeToast(
            weToastType = WeToastType.LOADING,
            message = "加载中"
        )
    }
}

@Composable
private fun YoutubeDLScreenUi(
    uiState: YoutubeDLScreenUiState,
    onAction: (YoutubeDLScreenAction) -> Unit
){
    val context = LocalContext.current
    WeScaffold(
        topBar = {
            WeTopNavigationBar(
                title = stringResource(com.laomuji666.compose.res.R.string.string_demo_screen_youtubedl_demo)
            )
            WeTableRowOutline(
                weTableRowOutlineType = WeTableRowOutlineType.FULL
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeTableInput(
                title = "URL",
                value = uiState.url,
                tip = "请输入视频链接",
                onValueChange = {
                    onAction(YoutubeDLScreenAction.SetUrl(it))
                },
                onImeNext = {}
            )
            Spacer(modifier = Modifier.height(30.dp))
            WeButton(
                text = "下载视频",
                weButtonType = WeButtonType.BIG
            ) {
                onAction(YoutubeDLScreenAction.OnDownloadVideoClick)
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(uiState.downloadInfo.size){ index ->
                    val downloadInfo = uiState.downloadInfo[index]
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                        downloadInfo.openVideo(context = context)
                    }) {
                        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Text(text = "id: ${downloadInfo.id}")
                            Text(text = "标题: ${downloadInfo.title}")
                            Text(text = "错误: ${downloadInfo.isError}")
                            Text(text = "下载完成: ${downloadInfo.isDone}")
                            Text(text = "时长: ${downloadInfo.getDurationText()}")
                            Text(text = "文件大小: ${downloadInfo.getFileSizeText()}")
                            Text(text = "进度: ${downloadInfo.getProgressText()}")
                            Text(text = "文件名: ${downloadInfo.filename}")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewYoutubeDLScreen() {
    QuicklyTheme {
        YoutubeDLScreenUi(
            uiState = YoutubeDLScreenUiState(
                isLoading = true
            ),
            onAction = {}
        )
    }
}