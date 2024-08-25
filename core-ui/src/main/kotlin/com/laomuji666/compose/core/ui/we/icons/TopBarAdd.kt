package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.TopBarAdd: ImageVector
    get() {
        if (_TopBarAdd != null) {
            return _TopBarAdd!!
        }
        _TopBarAdd = ImageVector.Builder(
            name = "WeIcons.TopBarAdd",
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
                moveTo(12f, 2f)
                curveTo(17.523f, 2f, 22f, 6.477f, 22f, 12f)
                curveTo(22f, 17.523f, 17.523f, 22f, 12f, 22f)
                curveTo(6.477f, 22f, 2f, 17.523f, 2f, 12f)
                curveTo(2f, 6.477f, 6.477f, 2f, 12f, 2f)
                close()
                moveTo(12f, 3.2f)
                curveTo(16.86f, 3.2f, 20.8f, 7.14f, 20.8f, 12f)
                curveTo(20.8f, 16.86f, 16.86f, 20.8f, 12f, 20.8f)
                curveTo(7.14f, 20.8f, 3.2f, 16.86f, 3.2f, 12f)
                curveTo(3.2f, 7.14f, 7.14f, 3.2f, 12f, 3.2f)
                close()
                moveTo(12.6f, 11.4f)
                lineTo(12.6f, 7f)
                lineTo(11.4f, 7f)
                lineTo(11.4f, 11.4f)
                lineTo(7f, 11.4f)
                lineTo(7f, 12.6f)
                lineTo(11.4f, 12.6f)
                lineTo(11.4f, 17f)
                lineTo(12.6f, 17f)
                lineTo(12.6f, 12.6f)
                lineTo(17f, 12.6f)
                lineTo(17f, 11.4f)
                lineTo(12.6f, 11.4f)
                close()
            }
        }.build()

        return _TopBarAdd!!
    }

@Suppress("ObjectPropertyName")
private var _TopBarAdd: ImageVector? = null
