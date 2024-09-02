package com.laomuji666.compose.core.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.abs

enum class BannerViewScrollType{
    NONE,
    SCROLL_NEXT,
    SCROLL_LAST
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T>BannerView(
    modifier: Modifier = Modifier,
    bannerList: List<T>,
    initialPage: Int = 0,
    verticalPadding: Dp = 20.dp,
    horizontalPadding: Dp = verticalPadding,
    contentPadding: PaddingValues = PaddingValues(horizontal = 60.dp),
    scrollType: BannerViewScrollType = BannerViewScrollType.SCROLL_NEXT,
    timeMillis: Long = 1000,
    contentSizeAnim: FiniteAnimationSpec<IntSize> = tween(500),
    scrollAnim: AnimationSpec<Float> = tween(500),
    looperBanner: Boolean = true,
    dragWidth:Float = 20.dp.value,
    content: @Composable (T)->Unit
){
    val dataList = ArrayList<T>()
    if(looperBanner && bannerList.size > 1){
        dataList.add(bannerList[bannerList.size - 2])
        dataList.add(bannerList[bannerList.size - 1])
        dataList.addAll(bannerList)
        dataList.add(bannerList[0])
        dataList.add(bannerList[1])
    }else{
        dataList.addAll(bannerList)
    }

    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = { dataList.size },
        initialPage = if(looperBanner) initialPage + 2 else initialPage
    )

    var dragStartX by remember { mutableFloatStateOf(0f) }

    HorizontalPager(
        modifier = modifier.pointerInput(Unit){
            detectHorizontalDragGestures(
                onDragStart = {
                    dragStartX = 0f
                },
                onDragEnd = {
                    if(dragStartX > 0){
                        coroutineScope.launch {
                            lastPage(
                                pagerState = pagerState,
                                looperBanner = looperBanner,
                                dataListSize = dataList.size,
                                scrollAnim = scrollAnim
                            )
                        }
                    }else{
                        if(abs(dragStartX) > dragWidth){
                            coroutineScope.launch {
                                nextPage(
                                    pagerState = pagerState,
                                    looperBanner = looperBanner,
                                    dataListSize = dataList.size,
                                    scrollAnim = scrollAnim
                                )
                            }
                        }
                    }
                },
                onHorizontalDrag = { _, dragAmount ->
                    dragStartX += dragAmount
                }
            )
        },
        state = pagerState,
        contentPadding = contentPadding,
        userScrollEnabled = false
    ) {
        Box(modifier = Modifier
            .padding(
                horizontal = if (it == pagerState.currentPage) 0.dp else horizontalPadding,
                vertical = if (it == pagerState.currentPage) 0.dp else verticalPadding
            )
            .animateContentSize(animationSpec = contentSizeAnim)
        ){
            content(dataList[it])
        }
    }
    LaunchedEffect(dragStartX) {
        while (isActive){
            delay(timeMillis)
            when(scrollType){
                BannerViewScrollType.NONE -> {}
                BannerViewScrollType.SCROLL_NEXT -> {
                    nextPage(
                        pagerState = pagerState,
                        looperBanner = looperBanner,
                        dataListSize = dataList.size,
                        scrollAnim = scrollAnim
                    )
                }
                BannerViewScrollType.SCROLL_LAST -> {
                    lastPage(
                        pagerState = pagerState,
                        looperBanner = looperBanner,
                        dataListSize = dataList.size,
                        scrollAnim = scrollAnim
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private suspend fun nextPage(
    pagerState: PagerState,
    looperBanner: Boolean,
    dataListSize: Int,
    scrollAnim: AnimationSpec<Float>
){
    var targetIndex = pagerState.currentPage
    if(looperBanner){
        if(targetIndex == dataListSize - 3){
            pagerState.animateScrollToPage(
                page = targetIndex + 1,
                animationSpec = scrollAnim
            )
            pagerState.scrollToPage(page = 2)
        }else{
            pagerState.animateScrollToPage(
                page = targetIndex + 1,
                animationSpec = scrollAnim
            )
        }
    }else{
        targetIndex = if(targetIndex == dataListSize - 1) 0 else targetIndex + 1
        pagerState.animateScrollToPage(
            page = targetIndex,
            animationSpec = scrollAnim
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private suspend fun lastPage(
    pagerState: PagerState,
    looperBanner: Boolean,
    dataListSize: Int,
    scrollAnim: AnimationSpec<Float>
){
    var targetIndex = pagerState.currentPage
    if(looperBanner){
        if(targetIndex == 2){
            pagerState.animateScrollToPage(
                page = targetIndex - 1,
                animationSpec = scrollAnim
            )
            pagerState.scrollToPage(page = dataListSize - 3)
        }else{
            pagerState.animateScrollToPage(
                page = targetIndex - 1,
                animationSpec = scrollAnim
            )
        }

    }else{
        targetIndex = if(targetIndex == 0) dataListSize - 1 else targetIndex - 1
        pagerState.animateScrollToPage(
            page = targetIndex,
            animationSpec = scrollAnim
        )
    }
}

@Preview
@Composable
fun PreviewBannerView(){
    BannerView(
        modifier = Modifier.height(200.dp),
        bannerList = listOf(
            Color.Red,
            Color.Black,
            Color.Blue,
            Color.Cyan,
            Color.LightGray
        )
    ) {
        Box(modifier = Modifier
            .background(it)
            .fillMaxSize()){
        }
    }
}
