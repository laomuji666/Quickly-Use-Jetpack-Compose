package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.offset
import androidx.compose.ui.window.Popup
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.Done
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import com.laomuji666.compose.core.ui.we2.widget.WeListOutline
import com.laomuji666.compose.core.ui.we2.widget.WeListOutlineType

@Composable
fun WePopMenu(
    onDismissRequest: ()->Unit = {},
    content: @Composable ColumnScope.()->Unit
){
    Popup(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .padding(end = WeTheme.weDimens.paddingHorizontalDp / 2)
                .width(WeTheme.weDimens.toastSize)
                .drawBubble(
                    arrowWidth = WeTheme.weDimens.toastSize / 10,
                    arrowHeight = WeTheme.weDimens.toastSize / 16,
                    arrowOffset = WeTheme.weDimens.toastSize / 10 * 8,
                    color = WeTheme.weColorScheme.popMenuBackgroundColor
                )
                .clip(RoundedCornerShape(WeTheme.weDimens.buttonRoundedCornerDp))
                .background(WeTheme.weColorScheme.popMenuBackgroundColor)
        ) {
            content()
        }
    }
}

@Composable
fun WePopMenuItem(
    text: String,
    imageVector: ImageVector,
    onClick:()->Unit = {}
){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(WeTheme.weDimens.rowHeightDp)
                .clickable { onClick() }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = imageVector,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(WeTheme.weColorScheme.onPopMenuBackgroundColor),
                modifier = Modifier
                    .padding(horizontal = WeTheme.weDimens.paddingHorizontalDp / 2)
                    .size(WeTheme.weDimens.iconHeightDp)
            )
            Text(
                text = text,
                style = WeTheme.weTypography.smallText,
                color = WeTheme.weColorScheme.onPopMenuBackgroundColor
            )
        }
        Row {
            Spacer(modifier = Modifier.width(WeTheme.weDimens.iconHeightDp))
            WeListOutline(
                weListOutlineType = WeListOutlineType.PADDING_HORIZONTAL,
            )
        }

    }

}

@Composable
fun Modifier.drawBubble(
    arrowWidth: Dp,
    arrowHeight: Dp,
    arrowOffset: Dp,
    color: Color
): Modifier {
    val arrowWidthPx: Float
    val arrowHeightPx: Float
    val arrowOffsetPx: Float
    with(LocalDensity.current) {
        arrowWidthPx = arrowWidth.toPx()
        arrowHeightPx = arrowHeight.toPx()
        arrowOffsetPx = arrowOffset.toPx()
    }
    val shape = remember(arrowWidth, arrowHeight, arrowOffset) {
        GenericShape { _: Size, _: LayoutDirection ->
            moveTo(arrowOffsetPx, arrowHeightPx)
            lineTo(arrowOffsetPx + arrowWidthPx / 2, 0f)
            lineTo(arrowOffsetPx + arrowWidthPx, arrowHeightPx)
        }
    }
    return this then Modifier
        .background(color, shape)
        .layout { measurable, constraints ->
            val offsetX = 0
            val offsetY = arrowHeightPx.toInt()
            val placeable = measurable.measure(
                constraints.offset(
                    horizontal = -offsetX,
                    vertical = -offsetY
                )
            )
            val width = constraints.constrainWidth(placeable.width + offsetX)
            val height = constraints.constrainHeight(placeable.height + offsetY)
            val posX = 0
            val posY = arrowHeightPx.toInt()
            layout(width, height) {
                placeable.placeRelative(posX, posY)
            }
        }
}


@PreviewLightDark
@Composable
fun PreviewWePopMenu(){
    QuicklyTheme {
        WePopMenu{
            WePopMenuItem(
                text = "发起群聊",
                imageVector = WeIcons.Done
            )
            WePopMenuItem(
                text = "添加朋友",
                imageVector = WeIcons.Done
            )
            WePopMenuItem(
                text = "扫一扫",
                imageVector = WeIcons.Done
            )
            WePopMenuItem(
                text = "收付款",
                imageVector = WeIcons.Done
            )
        }
    }
}