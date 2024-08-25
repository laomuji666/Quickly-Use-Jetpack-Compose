package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.ArrowLeft
import com.laomuji666.compose.core.ui.we.icons.TopBarAdd
import com.laomuji666.compose.core.ui.we.icons.TopBarMenu
import com.laomuji666.compose.core.ui.we.icons.TopBarSearch


/**
 * 参考微信的顶部导航栏设计图
 * 微信设计图的手机状态栏44dp,这里使用的是statusBarsPadding,在不同设备上的效果会更好一些.
 */
@Composable
fun WeTopBar(
    modifier: Modifier = Modifier,
    title:String = "",
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(WeTheme.weColorScheme.backgroundColor)
            .statusBarsPadding()
            .padding(bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .padding(horizontal = 5.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.width(90.dp)) {
                onBackClick?.let {
                    WeTopBarAction(
                        onActionClick = it,
                        imageVector = WeIcons.ArrowLeft
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                style = WeTheme.weTypography.mediumTitle,
                color = WeTheme.weColorScheme.onBackgroundColor
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.width(90.dp),
                horizontalArrangement = Arrangement.End
            ){
                actions()
            }
        }
    }
}

@Composable
fun WeTopBarAction(
    onActionClick:()->Unit = {},
    imageVector: ImageVector
){
    Box(modifier = Modifier
        .fillMaxHeight()
        .width(40.dp)
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
            colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onBackgroundColor),
            modifier = Modifier
                .align(Alignment.Center)
                .size(26.dp),
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTopBar1(){
    QuicklyTheme {
        Column {
            WeTopBar(
                title = "标题",
                onBackClick = {},
                actions = {
                    WeTopBarAction(
                        imageVector = WeIcons.TopBarMenu
                    )
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewWeTopBar2(){
    QuicklyTheme {
        Column {
            WeTopBar(
                title = "标题",
                actions = {
                    WeTopBarAction(
                        imageVector = WeIcons.TopBarSearch
                    )
                    WeTopBarAction(
                        imageVector = WeIcons.TopBarAdd
                    )
                }
            )
        }
    }
}