package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.Done: ImageVector
    get() {
        if (_Done != null) {
            return _Done!!
        }
        _Done = ImageVector.Builder(
            name = "WeIcons.Done",
            defaultWidth = 40.dp,
            defaultHeight = 40.dp,
            viewportWidth = 40f,
            viewportHeight = 40f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0.9f,
                strokeAlpha = 0.9f
            ) {
                moveTo(16.314f, 31.196f)
                lineTo(36.82f, 10.69f)
                lineTo(34.463f, 8.333f)
                lineTo(15.607f, 27.19f)
                lineTo(7.357f, 18.94f)
                lineTo(5f, 21.297f)
                lineTo(14.899f, 31.196f)
                curveTo(15.29f, 31.587f, 15.923f, 31.587f, 16.314f, 31.196f)
            }
        }.build()

        return _Done!!
    }

@Suppress("ObjectPropertyName")
private var _Done: ImageVector? = null
