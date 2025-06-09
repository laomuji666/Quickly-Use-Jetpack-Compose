package com.laomuji888.compose.core.ui.view.banner

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laomuji888.compose.res.R

/**
 * 支持任意数据类型的banner
 * 等待重构
 *
 * @param height Banner的高度
 * @param bannerList Banner的数据源
 * @param initialPage 初始页面
 * @param scrollType 滑动类型
 * @param scrollTimeMillis 滑动时间
 * @param animDurationMillis 动画时长
 * @param contentPaddingHorizontal item的横向padding.
 * @param xScale 非当前项的x轴缩放
 * @param yScale 非当前项的y轴缩放
 * @param looperBanner 是否循环
 * @param content item的内容
 * @author laomuji666
 * @since 2025/5/23
 */
@Composable
fun <T> Banner(
    height: Dp,
    bannerList: List<T>,
    initialPage: Int = 0,
    scrollType: BannerType = BannerType.SCROLL_NEXT,
    scrollTimeMillis: Long = 1000,
    animDurationMillis: Int = 500,
    contentPaddingHorizontal: Dp = 20.dp,
    xScale: Float = 0.95f,
    yScale: Float = 0.8f,
    looperBanner: Boolean = true,
    bannerState: BannerState = rememberBannerState(
        initialPage = initialPage,
        scrollTimeMillis = scrollTimeMillis,
        scrollType = scrollType,
        currentPage = initialPage
    ),
    content: @Composable (T) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { bannerList.size },
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.height(height),
        contentPadding = PaddingValues(horizontal = contentPaddingHorizontal),
    ) { page ->
        val isSelected = page == pagerState.currentPage
        val xScaleAnim by getScaleAnim(isSelected, xScale, animDurationMillis)
        val yScaleAnim by getScaleAnim(isSelected, yScale, animDurationMillis)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = xScaleAnim
                    scaleY = yScaleAnim
                },
        ) {
            content(bannerList[page])
        }
    }


    //同步currentPage
    LaunchedEffect(pagerState.currentPage) {
        if (bannerState.currentPage != pagerState.currentPage) {
            bannerState.currentPage = pagerState.currentPage
        }
    }
}

@Composable
private fun getScaleAnim(isSelected: Boolean, scale: Float, animDurationMillis: Int): State<Float> {
    return animateFloatAsState(
        targetValue = if (isSelected) 1f else scale,
        animationSpec = tween(animDurationMillis),
        label = "scale"
    )
}


@Preview
@Composable
fun PreviewBanner() {
    Banner(
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
                painter = painterResource(R.mipmap.ic_launcher),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
            )
        }
    }
}
