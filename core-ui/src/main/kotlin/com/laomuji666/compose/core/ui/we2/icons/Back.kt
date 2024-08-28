package com.laomuji666.compose.core.ui.we2.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons

val WeIcons.Back: ImageVector
    get() {
        if (_Back != null) {
            return _Back!!
        }
        _Back = ImageVector.Builder(
            name = "WeIcons.Back",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.9f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(16f, 19.438f)
                lineTo(14.955f, 20.5f)
                lineTo(7.281f, 12.701f)
                curveTo(6.898f, 12.312f, 6.898f, 11.688f, 7.281f, 11.299f)
                lineTo(14.955f, 3.5f)
                lineTo(16f, 4.563f)
                lineTo(8.682f, 12f)
                lineTo(16f, 19.438f)
                close()
            }
        }.build()

        return _Back!!
    }

@Suppress("ObjectPropertyName")
private var _Back: ImageVector? = null
