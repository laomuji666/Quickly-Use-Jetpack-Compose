package com.laomuji666.compose.feature.youtubedl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.laomuji666.compose.core.ui.clickableDebounce
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Add
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutline
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutlineType
import com.laomuji666.compose.core.ui.we.widget.WeToast
import com.laomuji666.compose.core.ui.we.widget.WeToastType
import com.laomuji666.compose.core.ui.we.widget.WeTopActionBar
import com.laomuji666.compose.core.ui.we.widget.WeTopNavigationBarAction
import com.laomuji666.compose.feature.video.VideoPlayActivity
import com.laomuji666.compose.feature.youtubedl.model.DownloadInfo
import com.laomuji666.compose.res.R

@Composable
fun YoutubeDLScreen(
    viewModel: YoutubeDLScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    YoutubeDLScreenUi(
        uiState = uiState,
        onAction = viewModel::onAction
    )

    if (uiState.isLoading) {
        WeToast(
            weToastType = WeToastType.LOADING,
            message = stringResource(R.string.string_youtubedl_screen_loading)
        )
    }
}

@Composable
private fun YoutubeDLScreenUi(
    uiState: YoutubeDLScreenUiState,
    onAction: (YoutubeDLScreenAction) -> Unit
) {
    val context = LocalContext.current
    var showAddDownloadDialog by rememberSaveable { mutableStateOf(false) }
    if (showAddDownloadDialog) {
        AddDownloadDialog(
            onDismissRequest = {
                showAddDownloadDialog = false
            },
            url = uiState.url,
            onValueChange = {
                onAction(YoutubeDLScreenAction.SetUrl(it))
            },
            onDownloadVideoClick = {
                showAddDownloadDialog = false
                onAction(YoutubeDLScreenAction.OnDownloadVideoClick)
            }
        )
    }
    WeScaffold(
        topBar = {
            WeTopActionBar(
                title = stringResource(R.string.string_demo_screen_youtubedl_demo),
                actions = {
                    WeTopNavigationBarAction(
                        imageVector = WeIcons.Add,
                        onActionClick = {
                            showAddDownloadDialog = true
                        }
                    )
                }
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
            LazyColumn {
                items(uiState.downloadInfo) { item ->
                    DownloadInfoItemView(item) { downloadInfo ->
                        if (downloadInfo.isDone) {
                            VideoPlayActivity.open(context, downloadInfo.filename)
                        } else {
                            onAction(YoutubeDLScreenAction.SwitchDownloadVideo(downloadInfo))
                        }
                    }
                }
            }
        }
    }


}

@Composable
private fun DownloadInfoItemView(
    downloadInfo: DownloadInfo,
    onClick: (DownloadInfo) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .aspectRatio(1.77f)
            .clip(RoundedCornerShape(12.dp))
            .background(WeTheme.colorScheme.tableRowBackground)
    ) {
        downloadInfo.thumbnail?.let {
            val imageUrl = it.replace("http://", "https://")
            val imageRequest = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .diskCacheKey(imageUrl)
                .build()
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(4.dp))
                    .background(WeTheme.colorScheme.fontColorDark.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = downloadInfo.title,
                    style = WeTheme.typography.desc,
                    color = WeTheme.colorScheme.background,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(WeTheme.colorScheme.fontColorDark.copy(alpha = 0.5f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = downloadInfo.getFileSizeText(),
                        style = WeTheme.typography.footnote,
                        color = WeTheme.colorScheme.background,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(WeTheme.colorScheme.fontColorDark.copy(alpha = 0.5f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = downloadInfo.getDurationText(),
                        style = WeTheme.typography.footnote,
                        color = WeTheme.colorScheme.background,
                    )
                }
            }
            val centerText = if (downloadInfo.isError) {
                stringResource(R.string.string_youtubedl_screen_download_error)
            } else if (downloadInfo.isDone) {
                stringResource(R.string.string_youtubedl_screen_download_done)
            } else if (!downloadInfo.isDownloading) {
                stringResource(R.string.string_youtubedl_screen_download_stop)
            } else {
                downloadInfo.getProgressText()
            }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(WeTheme.colorScheme.fontColorDark.copy(alpha = 0.5f))
                    .size(80.dp)
                    .clickableDebounce {
                        onClick(downloadInfo)
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = centerText,
                    style = WeTheme.typography.emTitle,
                    color = WeTheme.colorScheme.background,
                )
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
                downloadInfo = listOf(
                    DownloadInfo(
                        url = "",
                        title = "标题1",
                        isError = false,
                        isDone = false,
                        duration = 100.5,
                        fileSize = 1024242411.5,
                        progress = 50f,
                        filename = "下载中"
                    ),
                    DownloadInfo(
                        url = "",
                        title = "标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2",
                        isError = true,
                        isDone = false,
                        duration = 205.5,
                        fileSize = 2310242424.5,
                        progress = 0f,
                        filename = "下载失败"
                    ),
                    DownloadInfo(
                        url = "",
                        title = "标题3",
                        isError = false,
                        isDone = true,
                        duration = 520.5,
                        fileSize = 110242424.5,
                        progress = 100f,
                        filename = "下载成功"
                    ),
                )
            ),
            onAction = {}
        )
    }
}