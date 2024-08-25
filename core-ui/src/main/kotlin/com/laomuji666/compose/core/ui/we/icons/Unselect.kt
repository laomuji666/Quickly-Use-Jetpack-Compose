package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.Unselect: ImageVector
    get() {
        if (_Unselect != null) {
            return _Unselect!!
        }
        _Unselect = ImageVector.Builder(
            name = "WeIcons.Unselect",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFC9C9C9)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(0f, 12.148f)
                curveTo(0f, 5.372f, 5.372f, 0f, 11.852f, 0f)
                curveTo(18.628f, 0f, 24f, 5.372f, 24f, 12.148f)
                curveTo(24f, 18.628f, 18.628f, 24f, 11.852f, 24f)
                curveTo(5.372f, 24f, 0f, 18.628f, 0f, 12.148f)
                close()
                moveTo(11.852f, 1.2f)
                curveTo(17.898f, 1.2f, 22.8f, 6.102f, 22.8f, 12.148f)
                quadTo(22.8f, 14.292f, 21.953f, 16.259f)
                quadTo(21.129f, 18.17f, 19.626f, 19.648f)
                quadTo(18.113f, 21.135f, 16.148f, 21.951f)
                quadTo(14.104f, 22.8f, 11.852f, 22.8f)
                curveTo(5.969f, 22.8f, 1.2f, 18.031f, 1.2f, 12.148f)
                quadTo(1.2f, 9.896f, 2.049f, 7.852f)
                quadTo(2.865f, 5.887f, 4.352f, 4.374f)
                quadTo(5.83f, 2.871f, 7.741f, 2.047f)
                quadTo(9.708f, 1.2f, 11.852f, 1.2f)
                close()
            }
        }.build()

        return _Unselect!!
    }

@Suppress("ObjectPropertyName")
private var _Unselect: ImageVector? = null
