package com.laomuji1999.compose.core.ui.view.banner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

class BannerState(
    val initialPage: Int,
    val scrollTimeMillis: Long,
    val scrollType: BannerType,
    var currentPage: Int = initialPage,
) {
    companion object {
        val Saver: Saver<BannerState, *> = listSaver(save = {
            listOf(
                it.initialPage,
                it.scrollTimeMillis,
                it.scrollType,
                it.currentPage,
            )
        }, restore = {
            BannerState(
                initialPage = it[0] as Int,
                scrollTimeMillis = it[1] as Long,
                scrollType = it[2] as BannerType,
                currentPage = it[3] as Int,
            )
        })
    }
}

@Composable
fun rememberBannerState(
    initialPage: Int = 0,
    scrollTimeMillis: Long = 1000,
    scrollType: BannerType = BannerType.SCROLL_NEXT,
    currentPage: Int = initialPage,
): BannerState {
    return rememberSaveable(saver = BannerState.Saver) {
        BannerState(
            initialPage = initialPage,
            scrollTimeMillis = scrollTimeMillis,
            scrollType = scrollType,
            currentPage = currentPage
        )
    }
}