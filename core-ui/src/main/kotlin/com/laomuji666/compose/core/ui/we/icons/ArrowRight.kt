package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.ArrowRight: ImageVector
    get() {
        if (_ArrowRight != null) {
            return _ArrowRight!!
        }
        _ArrowRight = ImageVector.Builder(
            name = "Arrowright",
            defaultWidth = 12.dp,
            defaultHeight = 24.dp,
            viewportWidth = 12f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.3f
            ) {
                moveTo(7.879f, 12.005f)
                lineTo(2.454f, 17.43f)
                lineTo(3.515f, 18.491f)
                lineTo(9.293f, 12.712f)
                curveTo(9.683f, 12.322f, 9.683f, 11.689f, 9.293f, 11.298f)
                lineTo(3.515f, 5.52f)
                lineTo(2.454f, 6.581f)
                lineTo(7.879f, 12.005f)
            }
        }.build()

        return _ArrowRight!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowRight: ImageVector? = null
