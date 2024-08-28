package com.laomuji666.compose.core.ui.we2.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlin.Suppress

val WeIcons.ContactsSelect: ImageVector
    get() {
        if (_ContactsSelect != null) {
            return _ContactsSelect!!
        }
        _ContactsSelect = ImageVector.Builder(
            name = "WeIcons.ContactsSelect",
            defaultWidth = 29.dp,
            defaultHeight = 28.dp,
            viewportWidth = 29f,
            viewportHeight = 28f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF39CD80)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(1.792f, 23.5f)
                curveTo(1.792f, 24.052f, 2.239f, 24.5f, 2.792f, 24.5f)
                horizontalLineTo(22.958f)
                curveTo(23.511f, 24.5f, 23.958f, 24.052f, 23.958f, 23.5f)
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
                verticalLineTo(23.5f)
                close()
                moveTo(27.458f, 16.917f)
                horizontalLineTo(23.958f)
                verticalLineTo(18.667f)
                horizontalLineTo(27.458f)
                verticalLineTo(16.917f)
                close()
                moveTo(21.625f, 13.417f)
                horizontalLineTo(27.458f)
                verticalLineTo(15.167f)
                horizontalLineTo(21.625f)
                verticalLineTo(13.417f)
                close()
                moveTo(27.458f, 9.917f)
                horizontalLineTo(19.292f)
                verticalLineTo(11.667f)
                horizontalLineTo(27.458f)
                verticalLineTo(9.917f)
                close()
            }
        }.build()

        return _ContactsSelect!!
    }

@Suppress("ObjectPropertyName")
private var _ContactsSelect: ImageVector? = null
