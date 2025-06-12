package com.laomuji1999.compose.core.ui.view

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class BannerViewScrollType {
    NONE, SCROLL_NEXT, SCROLL_LAST
}

@Composable
fun <T> BannerView(
    height: Dp,
    bannerList: List<T>,
    initialPage: Int = 0,
    scrollType: BannerViewScrollType = BannerViewScrollType.SCROLL_NEXT,
    xScale: Float = 0.95f,
    yScale: Float = 0.8f,
    contentPaddingHorizontal: Dp = 20.dp,
    timeMillis: Long = 1000,
    animDurationMillis: Int = 500,
    looperBanner: Boolean = true,
    dragWidth: Float = 20.dp.value,
    content: @Composable (T) -> Unit
) {
    val dragWidthPx = with(LocalDensity.current) { dragWidth.dp.toPx() }

    // 构造循环数据
    val dataList = remember(bannerList, looperBanner) {
        buildList {
            if (looperBanner && bannerList.size > 1) {
                add(bannerList[bannerList.size - 2])
                add(bannerList[bannerList.size - 1])
                addAll(bannerList)
                add(bannerList[0])
                add(bannerList[1])
            } else {
                addAll(bannerList)
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    var dragStartX by remember { mutableFloatStateOf(0f) }
    var scrollFinish by remember { mutableStateOf(false) }

    val actualInitialPage = remember(looperBanner, initialPage) {
        if (looperBanner) initialPage + 2 else initialPage
    }

    val pagerState = rememberPagerState(
        pageCount = { dataList.size }, initialPage = actualInitialPage
    )

    HorizontalPager(
        modifier = Modifier
            .height(height)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragStart = { dragStartX = 0f }, onDragEnd = {
                    if (scrollFinish) return@detectHorizontalDragGestures

                    when {
                        dragStartX > dragWidthPx -> {
                            scrollFinish = true
                            coroutineScope.launch {
                                lastPage(
                                    pagerState,
                                    looperBanner,
                                    dataList.size,
                                    tween(animDurationMillis)
                                )
                                scrollFinish = false
                            }
                        }

                        dragStartX < -dragWidthPx -> {
                            scrollFinish = true
                            coroutineScope.launch {
                                nextPage(
                                    pagerState,
                                    looperBanner,
                                    dataList.size,
                                    tween(animDurationMillis)
                                )
                                scrollFinish = false
                            }
                        }
                    }
                }, onHorizontalDrag = { change, dragAmount ->
                    change.consume()
                    dragStartX += dragAmount
                })
            },
        state = pagerState,
        contentPadding = PaddingValues(horizontal = contentPaddingHorizontal),
        userScrollEnabled = false,
    ) { page ->
        val isSelected = page == pagerState.currentPage
        val xScaleAnim by animateFloatAsState(
            targetValue = if (isSelected) 1f else xScale,
            animationSpec = tween(500),
            label = "xScale"
        )
        val yScaleAnim by animateFloatAsState(
            targetValue = if (isSelected) 1f else yScale,
            animationSpec = tween(500),
            label = "yScale"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = xScaleAnim
                    scaleY = yScaleAnim
                }) {
            if (dataList.isNotEmpty()) {
                content(dataList[page.coerceIn(0, dataList.lastIndex)])
            }
        }
    }

    LaunchedEffect(dragStartX, scrollFinish) {
        while (isActive) {
            if (timeMillis < 1) {
                break
            }
            delay(timeMillis)
            when (scrollType) {
                BannerViewScrollType.SCROLL_NEXT -> {
                    nextPage(pagerState, looperBanner, dataList.size, tween(animDurationMillis))
                }

                BannerViewScrollType.SCROLL_LAST -> {
                    lastPage(pagerState, looperBanner, dataList.size, tween(animDurationMillis))
                }

                BannerViewScrollType.NONE -> Unit
            }
        }
    }

    //在回到页面时,解决动画还没有播放完的问题.
    LaunchedEffect(Unit) {
        pagerState.animateScrollToPage(
            page = pagerState.currentPage, animationSpec = tween(animDurationMillis)
        )
        movePage(scrollType, pagerState, looperBanner, dataList.size)
    }
}

private suspend fun nextPage(
    pagerState: PagerState,
    looperBanner: Boolean,
    dataListSize: Int,
    scrollAnim: AnimationSpec<Float>
) {
    val current = pagerState.currentPage
    val target =
        if (looperBanner) current + 1 else if (current == dataListSize - 1) 0 else current + 1

    pagerState.animateScrollToPage(page = target, animationSpec = scrollAnim)

    movePage(BannerViewScrollType.SCROLL_NEXT, pagerState, looperBanner, dataListSize)
}

private suspend fun movePage(
    scrollType: BannerViewScrollType,
    pagerState: PagerState,
    looperBanner: Boolean,
    dataListSize: Int,
) {
    if (!looperBanner) {
        return
    }
    val current = pagerState.currentPage
    when (scrollType) {
        BannerViewScrollType.SCROLL_NEXT -> {
            val jumpPage = when (current) {
                dataListSize - 2 -> 2
                dataListSize - 1 -> 1
                else -> null
            }
            jumpPage?.let { pagerState.scrollToPage(it) }
        }

        BannerViewScrollType.SCROLL_LAST -> {
            val jumpPage = when (current) {
                2 -> dataListSize - 2
                1 -> dataListSize - 1
                else -> null
            }
            jumpPage?.let { pagerState.scrollToPage(it) }
        }

        BannerViewScrollType.NONE -> Unit
    }
}

private suspend fun lastPage(
    pagerState: PagerState,
    looperBanner: Boolean,
    dataListSize: Int,
    scrollAnim: AnimationSpec<Float>
) {
    val current = pagerState.currentPage
    val target =
        if (looperBanner) current - 1 else if (current == 0) dataListSize - 1 else current - 1

    pagerState.animateScrollToPage(page = target, animationSpec = scrollAnim)

    movePage(BannerViewScrollType.SCROLL_LAST, pagerState, looperBanner, dataListSize)
}

@Preview
@Composable
fun PreviewBannerView() {
    BannerView(
        height = 90.dp, bannerList = listOf(
            Color.Red, Color.Yellow, Color.Blue, Color.Cyan
        )
    ) {
        Box(
            modifier = Modifier
                .background(it)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(com.laomuji1999.compose.res.R.mipmap.ic_launcher),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
            )
        }
    }
}
