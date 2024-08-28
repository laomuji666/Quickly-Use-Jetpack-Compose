package com.laomuji666.compose.core.ui.we2.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlin.Suppress

val WeIcons.DiscoverUnselect: ImageVector
    get() {
        if (_DiscoverUnselect != null) {
            return _DiscoverUnselect!!
        }
        _DiscoverUnselect = ImageVector.Builder(
            name = "WeIcons.DiscoverUnselect",
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
                moveTo(14.375f, 24.267f)
                curveTo(20.045f, 24.267f, 24.642f, 19.67f, 24.642f, 14f)
                curveTo(24.642f, 8.33f, 20.045f, 3.733f, 14.375f, 3.733f)
                curveTo(8.705f, 3.733f, 4.108f, 8.33f, 4.108f, 14f)
                curveTo(4.108f, 19.67f, 8.705f, 24.267f, 14.375f, 24.267f)
                close()
                moveTo(14.375f, 25.667f)
                curveTo(7.932f, 25.667f, 2.708f, 20.443f, 2.708f, 14f)
                curveTo(2.708f, 7.557f, 7.932f, 2.333f, 14.375f, 2.333f)
                curveTo(20.818f, 2.333f, 26.042f, 7.557f, 26.042f, 14f)
                curveTo(26.042f, 20.443f, 20.818f, 25.667f, 14.375f, 25.667f)
                close()
                moveTo(13.352f, 12.977f)
                lineTo(11.187f, 17.188f)
                lineTo(15.398f, 15.023f)
                lineTo(17.563f, 10.812f)
                lineTo(13.352f, 12.977f)
                close()
                moveTo(12.313f, 11.938f)
                lineTo(19.187f, 8.403f)
                curveTo(19.354f, 8.317f, 19.553f, 8.317f, 19.72f, 8.403f)
                curveTo(20.007f, 8.55f, 20.12f, 8.902f, 19.972f, 9.188f)
                lineTo(16.437f, 16.062f)
                lineTo(9.563f, 19.597f)
                curveTo(9.396f, 19.683f, 9.197f, 19.683f, 9.03f, 19.597f)
                curveTo(8.743f, 19.45f, 8.63f, 19.098f, 8.778f, 18.812f)
                lineTo(12.313f, 11.938f)
                close()
            }
        }.build()

        return _DiscoverUnselect!!
    }

@Suppress("ObjectPropertyName")
private var _DiscoverUnselect: ImageVector? = null
