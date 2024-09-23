package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.icons.ChatsSelect
import com.laomuji666.compose.core.ui.we.icons.ContactsSelect
import com.laomuji666.compose.core.ui.we.icons.MeSelect
import com.laomuji666.compose.core.ui.we.icons.WeIcons

@Composable
fun WeContactItem(
    painter: Painter,
    text: String,
    onClick:()->Unit = {}
){
    WeTableRow(
        start = {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(WeTheme.dimens.contactIconSize)
                    .clip(RoundedCornerShape(WeTheme.dimens.contactIconRoundedCornerDp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(WeTheme.dimens.listPaddingHorizontal))
        },
        center = {
            Column {
                Text(
                    text = text,
                    style = WeTheme.typography.title,
                    color = WeTheme.colorScheme.fontColor90
                )
            }
        },
        weTableRowType = WeTableRowType.SINGLE,
        outlineModifier = Modifier.padding(start = WeTheme.dimens.contactIconSize + WeTheme.dimens.listPaddingHorizontal * 2),
        weTableRowOutlineType = WeTableRowOutlineType.FULL,
        onClick = onClick,
        showClickIndication = true
    )
}

@PreviewLightDark
@Composable
fun PreviewWeContactItem(){
    DefaultWeTheme {
        Column {
            WeContactItem(
                painter = rememberVectorPainter(image = WeIcons.ChatsSelect),
                text = "A"
            )
            WeContactItem(
                painter = rememberVectorPainter(image = WeIcons.ContactsSelect),
                text = "B"
            )
            WeContactItem(
                painter = rememberVectorPainter(image = WeIcons.MeSelect),
                text = "C"
            )
        }
    }
}