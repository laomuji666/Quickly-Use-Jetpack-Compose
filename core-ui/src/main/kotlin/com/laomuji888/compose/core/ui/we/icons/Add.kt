package com.laomuji888.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.Add: ImageVector
    get() {
        if (_Add != null) {
            return _Add!!
        }
        _Add = ImageVector.Builder(
            name = "WeIcons.Add",
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
                moveTo(2f, 12f)
                curveTo(2f, 17.523f, 6.477f, 22f, 12f, 22f)
                curveTo(17.523f, 22f, 22f, 17.523f, 22f, 12f)
                curveTo(22f, 6.477f, 17.523f, 2f, 12f, 2f)
                curveTo(6.477f, 2f, 2f, 6.477f, 2f, 12f)
                close()
                moveTo(20.8f, 12f)
                curveTo(20.8f, 16.86f, 16.86f, 20.8f, 12f, 20.8f)
                curveTo(7.14f, 20.8f, 3.2f, 16.86f, 3.2f, 12f)
                curveTo(3.2f, 7.14f, 7.14f, 3.2f, 12f, 3.2f)
                curveTo(16.86f, 3.2f, 20.8f, 7.14f, 20.8f, 12f)
                close()
                moveTo(17f, 11.4f)
                verticalLineTo(12.6f)
                horizontalLineTo(12.6f)
                verticalLineTo(17f)
                horizontalLineTo(11.4f)
                verticalLineTo(12.6f)
                horizontalLineTo(7f)
                verticalLineTo(11.4f)
                horizontalLineTo(11.4f)
                verticalLineTo(7f)
                horizontalLineTo(12.6f)
                verticalLineTo(11.4f)
                horizontalLineTo(17f)
                close()
            }
        }.build()

        return _Add!!
    }

@Suppress("ObjectPropertyName")
private var _Add: ImageVector? = null
