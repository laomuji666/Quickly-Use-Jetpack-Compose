package com.laomuji666.compose.core.ui.we.widget

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Add
import com.laomuji666.compose.core.ui.we.icons.Back
import com.laomuji666.compose.core.ui.we.icons.Search
import com.laomuji666.compose.core.ui.we.icons.WeIcons

@Composable
fun WeTopNavigationBar(
    title:String = "",
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
){
    Row(
        modifier = Modifier
            .background(WeTheme.colorScheme.topNavigationBarBackground)
            .statusBarsPadding()
            .fillMaxWidth()
            .height(WeTheme.dimens.navigationBarHeight)
            .padding(horizontal = WeTheme.dimens.navigationBarPaddingHorizontal)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.width(WeTheme.dimens.navigationBarActionWidth)) {
            onBackClick?.let {
                BackHandler {
                    it()
                }
                WeTopNavigationBarAction(
                    imageVector = WeIcons.Back,
                    onActionClick = it
                )
            }
        }
        Text(
            text = title,
            style = WeTheme.typography.emTitle,
            color = WeTheme.colorScheme.fontColor90,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.width(WeTheme.dimens.navigationBarActionWidth),
            horizontalArrangement = Arrangement.End
        ) {
            actions()
        }
    }
}

@Composable
fun WeTopNavigationBarAction(
    onActionClick:()->Unit = {},
    imageVector: ImageVector
){
    Box(modifier = Modifier
        .fillMaxHeight()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onActionClick
        )
    ){
        Image(
            imageVector = imageVector,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(WeTheme.colorScheme.fontColor90),
            modifier = Modifier
                .align(Alignment.Center)
                .size(WeTheme.dimens.navigationBarIconSize),
        )
    }
}

@Composable
fun WeTopNavigationBarSpace(){
    Spacer(modifier = Modifier.width(WeTheme.dimens.navigationBarActionPaddingWidth))
}

@PreviewLightDark
@Composable
fun PreviewWeTopNavigationBar(){
    DefaultWeTheme{
        WeTopNavigationBar(
            title = "标题",
            onBackClick = {},
            actions = {
                WeTopNavigationBarAction(
                    imageVector = WeIcons.Search
                )
                WeTopNavigationBarSpace()
                WeTopNavigationBarAction(
                    imageVector = WeIcons.Add
                )
            }
        )
    }
}