package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.ChatsUnselect: ImageVector
    get() {
        if (_ChatsUnselect != null) {
            return _ChatsUnselect!!
        }
        _ChatsUnselect = ImageVector.Builder(
            name = "WeIcons.ChatsUnselect",
            defaultWidth = 29.dp,
            defaultHeight = 28.dp,
            viewportWidth = 29f,
            viewportHeight = 28f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.9f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10.987f, 21.313f)
                lineTo(11.5f, 21.461f)
                curveTo(12.575f, 21.772f, 13.711f, 21.933f, 14.875f, 21.933f)
                curveTo(20.591f, 21.933f, 25.142f, 18.065f, 25.142f, 13.417f)
                curveTo(25.142f, 8.768f, 20.591f, 4.9f, 14.875f, 4.9f)
                curveTo(9.159f, 4.9f, 4.608f, 8.768f, 4.608f, 13.417f)
                curveTo(4.608f, 15.839f, 5.841f, 18.114f, 7.992f, 19.73f)
                lineTo(8.635f, 20.213f)
                lineTo(8.361f, 22.572f)
                lineTo(10.987f, 21.313f)
                close()
                moveTo(14.875f, 23.333f)
                curveTo(13.558f, 23.333f, 12.292f, 23.148f, 11.111f, 22.806f)
                lineTo(7.631f, 24.475f)
                curveTo(7.531f, 24.522f, 7.42f, 24.541f, 7.311f, 24.528f)
                curveTo(6.991f, 24.491f, 6.762f, 24.201f, 6.799f, 23.881f)
                lineTo(7.151f, 20.849f)
                curveTo(4.733f, 19.032f, 3.208f, 16.376f, 3.208f, 13.417f)
                curveTo(3.208f, 7.94f, 8.432f, 3.5f, 14.875f, 3.5f)
                curveTo(21.318f, 3.5f, 26.542f, 7.94f, 26.542f, 13.417f)
                curveTo(26.542f, 18.893f, 21.318f, 23.333f, 14.875f, 23.333f)
                close()
            }
        }.build()

        return _ChatsUnselect!!
    }

@Suppress("ObjectPropertyName")
private var _ChatsUnselect: ImageVector? = null
