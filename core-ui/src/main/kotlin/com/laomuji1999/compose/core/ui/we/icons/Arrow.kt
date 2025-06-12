package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.Arrow: ImageVector
    get() {
        if (_Arrow != null) {
            return _Arrow!!
        }
        _Arrow = ImageVector.Builder(
            name = "WeIcons.Arrow",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
            autoMirror = true
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.9f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(8.454f, 6.581f)
                lineTo(9.515f, 5.52f)
                lineTo(15.293f, 11.298f)
                curveTo(15.683f, 11.689f, 15.683f, 12.322f, 15.293f, 12.712f)
                lineTo(9.515f, 18.491f)
                lineTo(8.454f, 17.43f)
                lineTo(13.879f, 12.005f)
                lineTo(8.454f, 6.581f)
                close()
            }
        }.build()

        return _Arrow!!
    }

@Suppress("ObjectPropertyName")
private var _Arrow: ImageVector? = null
