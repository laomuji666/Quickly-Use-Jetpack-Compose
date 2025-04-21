package com.laomuji666.compose.core.ui.we.widget

import android.net.Uri
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeContactItem(
    avatar: Uri,
    text: String,
    onClick: () -> Unit,
) {
    val imageRequest = ImageRequest
        .Builder(LocalContext.current)
        .data(avatar)
        .diskCacheKey(avatar.toString())
        .build()

    WeTableRow(
        start = {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                placeholder = painterResource(id = com.laomuji666.compose.res.R.mipmap.ic_launcher),
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
        clickTimeout = 1500L,
        onClick = onClick,
    )
}

@PreviewLightDark
@Composable
fun PreviewWeContactItem() {
    DefaultWeTheme {
        Column {
            WeContactItem(
                "".toUri(),
                text = "A",
                onClick = {}
            )
            WeContactItem(
                "".toUri(),
                text = "B",
                onClick = {}
            )
            WeContactItem(
                "".toUri(),
                text = "C",
                onClick = {}
            )
        }
    }
}