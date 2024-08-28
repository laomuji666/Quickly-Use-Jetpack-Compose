package com.laomuji666.compose.core.ui.we2.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlin.Suppress

val WeIcons.Search: ImageVector
    get() {
        if (_Search != null) {
            return _Search!!
        }
        _Search = ImageVector.Builder(
            name = "WeIcons.Search",
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
                moveTo(15.453f, 16.401f)
                curveTo(14.243f, 17.4f, 12.691f, 18f, 11f, 18f)
                curveTo(7.134f, 18f, 4f, 14.866f, 4f, 11f)
                curveTo(4f, 7.134f, 7.134f, 4f, 11f, 4f)
                curveTo(14.866f, 4f, 18f, 7.134f, 18f, 11f)
                curveTo(18f, 12.743f, 17.363f, 14.336f, 16.31f, 15.561f)
                lineTo(20.071f, 19.322f)
                curveTo(20.266f, 19.517f, 20.266f, 19.834f, 20.071f, 20.029f)
                lineTo(19.929f, 20.171f)
                curveTo(19.734f, 20.366f, 19.417f, 20.366f, 19.222f, 20.171f)
                lineTo(15.453f, 16.401f)
                close()
                moveTo(16.8f, 11f)
                curveTo(16.8f, 14.203f, 14.203f, 16.8f, 11f, 16.8f)
                curveTo(7.797f, 16.8f, 5.2f, 14.203f, 5.2f, 11f)
                curveTo(5.2f, 7.797f, 7.797f, 5.2f, 11f, 5.2f)
                curveTo(14.203f, 5.2f, 16.8f, 7.797f, 16.8f, 11f)
                close()
            }
        }.build()

        return _Search!!
    }

@Suppress("ObjectPropertyName")
private var _Search: ImageVector? = null
