package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.ChatsSelect: ImageVector
    get() {
        if (_ChatsSelect != null) {
            return _ChatsSelect!!
        }
        _ChatsSelect = ImageVector.Builder(
            name = "WeIcons.ChatsSelect",
            defaultWidth = 29.dp,
            defaultHeight = 28.dp,
            viewportWidth = 29f,
            viewportHeight = 28f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF39CD80)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(14.875f, 23.333f)
                curveTo(21.318f, 23.333f, 26.542f, 18.893f, 26.542f, 13.417f)
                curveTo(26.542f, 7.94f, 21.318f, 3.5f, 14.875f, 3.5f)
                curveTo(8.432f, 3.5f, 3.208f, 7.94f, 3.208f, 13.417f)
                curveTo(3.208f, 16.376f, 4.733f, 19.032f, 7.151f, 20.849f)
                lineTo(6.799f, 23.881f)
                curveTo(6.762f, 24.201f, 6.991f, 24.491f, 7.311f, 24.528f)
                curveTo(7.42f, 24.541f, 7.531f, 24.522f, 7.631f, 24.475f)
                lineTo(11.111f, 22.806f)
                curveTo(12.292f, 23.148f, 13.558f, 23.333f, 14.875f, 23.333f)
                close()
            }
        }.build()

        return _ChatsSelect!!
    }

@Suppress("ObjectPropertyName")
private var _ChatsSelect: ImageVector? = null
