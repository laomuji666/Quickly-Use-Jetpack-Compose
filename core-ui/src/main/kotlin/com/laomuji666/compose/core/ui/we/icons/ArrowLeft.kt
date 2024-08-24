package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.ArrowLeft: ImageVector
    get() {
        if (_ArrowLeft != null) {
            return _ArrowLeft!!
        }
        _ArrowLeft = ImageVector.Builder(
            name = "We.ArrowLeft",
            defaultWidth = 12.dp,
            defaultHeight = 24.dp,
            viewportWidth = 12f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.9f
            ) {
                moveTo(2.682f, 12f)
                lineTo(10f, 4.563f)
                lineTo(8.955f, 3.5f)
                lineTo(1.281f, 11.299f)
                curveTo(0.898f, 11.688f, 0.898f, 12.312f, 1.281f, 12.701f)
                lineTo(8.955f, 20.5f)
                lineTo(10f, 19.438f)
                lineTo(2.682f, 12f)
            }
        }.build()
        return _ArrowLeft!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowLeft: ImageVector? = null
