package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.ContactsUnselect: ImageVector
    get() {
        if (_ContactsUnselect != null) {
            return _ContactsUnselect!!
        }
        _ContactsUnselect = ImageVector.Builder(
            name = "WeIcons.ContactsUnselect",
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
                moveTo(22.558f, 22.535f)
                verticalLineTo(23.1f)
                horizontalLineTo(3.192f)
                verticalLineTo(22.535f)
                curveTo(3.192f, 22.27f, 3.468f, 21.827f, 3.705f, 21.711f)
                lineTo(10.309f, 18.489f)
                curveTo(12.088f, 17.621f, 12.533f, 15.521f, 11.254f, 14.006f)
                lineTo(10.832f, 13.506f)
                curveTo(10.187f, 12.741f, 9.608f, 11.16f, 9.608f, 10.161f)
                verticalLineTo(8.166f)
                curveTo(9.608f, 6.364f, 11.074f, 4.9f, 12.875f, 4.9f)
                curveTo(14.678f, 4.9f, 16.142f, 6.364f, 16.142f, 8.167f)
                verticalLineTo(10.162f)
                curveTo(16.142f, 11.158f, 15.561f, 12.745f, 14.918f, 13.507f)
                lineTo(14.496f, 14.007f)
                curveTo(13.22f, 15.52f, 13.66f, 17.622f, 15.441f, 18.49f)
                lineTo(22.045f, 21.712f)
                curveTo(22.284f, 21.828f, 22.558f, 22.267f, 22.558f, 22.535f)
                close()
                moveTo(1.792f, 22.535f)
                verticalLineTo(23.333f)
                curveTo(1.792f, 23.978f, 2.314f, 24.5f, 2.958f, 24.5f)
                horizontalLineTo(22.792f)
                curveTo(23.436f, 24.5f, 23.958f, 23.978f, 23.958f, 23.333f)
                verticalLineTo(22.535f)
                curveTo(23.958f, 21.729f, 23.376f, 20.804f, 22.659f, 20.454f)
                lineTo(16.055f, 17.232f)
                curveTo(15.093f, 16.763f, 14.878f, 15.725f, 15.566f, 14.91f)
                lineTo(15.988f, 14.41f)
                curveTo(16.843f, 13.397f, 17.542f, 11.491f, 17.542f, 10.162f)
                verticalLineTo(8.167f)
                curveTo(17.542f, 5.592f, 15.452f, 3.5f, 12.875f, 3.5f)
                curveTo(10.303f, 3.5f, 8.208f, 5.589f, 8.208f, 8.166f)
                verticalLineTo(10.161f)
                curveTo(8.208f, 11.491f, 8.904f, 13.391f, 9.762f, 14.408f)
                lineTo(10.184f, 14.908f)
                curveTo(10.876f, 15.728f, 10.653f, 16.763f, 9.695f, 17.231f)
                lineTo(3.091f, 20.453f)
                curveTo(2.373f, 20.804f, 1.792f, 21.735f, 1.792f, 22.535f)
                close()
                moveTo(27.458f, 16.917f)
                horizontalLineTo(23.958f)
                verticalLineTo(18.317f)
                horizontalLineTo(27.458f)
                verticalLineTo(16.917f)
                close()
                moveTo(21.625f, 13.417f)
                horizontalLineTo(27.458f)
                verticalLineTo(14.817f)
                horizontalLineTo(21.625f)
                verticalLineTo(13.417f)
                close()
                moveTo(27.458f, 9.917f)
                horizontalLineTo(19.292f)
                verticalLineTo(11.317f)
                horizontalLineTo(27.458f)
                verticalLineTo(9.917f)
                close()
            }
        }.build()

        return _ContactsUnselect!!
    }

@Suppress("ObjectPropertyName")
private var _ContactsUnselect: ImageVector? = null
