package com.laomuji666.compose.core.ui.we2.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons

val WeIcons.MeUnselect: ImageVector
    get() {
        if (_MeUnselect != null) {
            return _MeUnselect!!
        }
        _MeUnselect = ImageVector.Builder(
            name = "WeIcons.MeUnselect",
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
                moveTo(23.808f, 23.1f)
                verticalLineTo(22.535f)
                curveTo(23.808f, 22.267f, 23.534f, 21.828f, 23.295f, 21.712f)
                lineTo(16.691f, 18.49f)
                curveTo(14.91f, 17.622f, 14.47f, 15.52f, 15.746f, 14.007f)
                lineTo(16.168f, 13.507f)
                curveTo(16.811f, 12.745f, 17.392f, 11.158f, 17.392f, 10.162f)
                verticalLineTo(8.167f)
                curveTo(17.392f, 6.364f, 15.928f, 4.9f, 14.125f, 4.9f)
                curveTo(12.324f, 4.9f, 10.858f, 6.364f, 10.858f, 8.166f)
                verticalLineTo(10.161f)
                curveTo(10.858f, 11.16f, 11.437f, 12.741f, 12.082f, 13.506f)
                lineTo(12.504f, 14.006f)
                curveTo(13.783f, 15.521f, 13.338f, 17.621f, 11.559f, 18.489f)
                lineTo(4.955f, 21.711f)
                curveTo(4.718f, 21.827f, 4.442f, 22.27f, 4.442f, 22.535f)
                verticalLineTo(23.1f)
                horizontalLineTo(23.808f)
                close()
                moveTo(3.042f, 23.333f)
                verticalLineTo(22.535f)
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
                verticalLineTo(23.333f)
                curveTo(25.208f, 23.978f, 24.686f, 24.5f, 24.042f, 24.5f)
                horizontalLineTo(4.208f)
                curveTo(3.564f, 24.5f, 3.042f, 23.978f, 3.042f, 23.333f)
                close()
            }
        }.build()

        return _MeUnselect!!
    }

@Suppress("ObjectPropertyName")
private var _MeUnselect: ImageVector? = null
