package com.laomuji666.compose.core.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T>BannerView(
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    timeMillis: Long = 2000,
    bannerList: List<T>,
    contentPadding: PaddingValues = PaddingValues(horizontal = 40.dp),
    content: @Composable (T)->Unit
){
    var currentIndex by rememberSaveable {
        mutableIntStateOf(initialPage)
    }
    val pagerState = rememberPagerState(
        pageCount = { bannerList.size },
        initialPage = initialPage
    )
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = contentPadding
    ) {
        content(bannerList[it])
    }
    LaunchedEffect(Unit) {
        while (isActive){
            delay(timeMillis)
            currentIndex = if(currentIndex == bannerList.size - 1) 0 else currentIndex + 1
            pagerState.animateScrollToPage(currentIndex)
        }
    }
}

@Preview
@Composable
fun PreviewBannerView(){
    BannerView(
        modifier = Modifier.height(150.dp),
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
