package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.MeSelect: ImageVector
    get() {
        if (_MeSelect != null) {
            return _MeSelect!!
        }
        _MeSelect = ImageVector.Builder(
            name = "WeIcons.MeSelect",
            defaultWidth = 29.dp,
            defaultHeight = 28.dp,
            viewportWidth = 29f,
            viewportHeight = 28f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF39CD80)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(4.042f, 24.5f)
                curveTo(3.489f, 24.5f, 3.042f, 24.052f, 3.042f, 23.5f)
                lineTo(3.042f, 22.535f)
                curveTo(3.042f, 21.735f, 3.623f, 20.804f, 4.341f, 20.453f)
                lineTo(10.945f, 17.231f)
                curveTo(11.903f, 16.763f, 12.126f, 15.728f, 11.434f, 14.908f)
                lineTo(11.012f, 14.408f)
                curveTo(10.154f, 13.391f, 9.458f, 11.491f, 9.458f, 10.161f)
                verticalLineTo(8.166f)
                curveTo(9.458f, 5.589f, 11.553f, 3.5f, 14.125f, 3.5f)
                curveTo(16.702f, 3.5f, 18.792f, 5.592f, 18.792f, 8.167f)
                verticalLineTo(10.162f)
                curveTo(18.792f, 11.491f, 18.093f, 13.397f, 17.238f, 14.41f)
                lineTo(16.816f, 14.91f)
                curveTo(16.128f, 15.725f, 16.343f, 16.763f, 17.305f, 17.232f)
                lineTo(23.909f, 20.454f)
                curveTo(24.626f, 20.804f, 25.208f, 21.729f, 25.208f, 22.535f)
                verticalLineTo(23.5f)
                curveTo(25.208f, 24.052f, 24.761f, 24.5f, 24.208f, 24.5f)
                horizontalLineTo(4.042f)
                close()
            }
        }.build()

        return _MeSelect!!
    }

@Suppress("ObjectPropertyName")
private var _MeSelect: ImageVector? = null
