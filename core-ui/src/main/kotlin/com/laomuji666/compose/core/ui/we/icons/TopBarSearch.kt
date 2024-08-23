package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.WeIcons
import kotlin.Suppress

val WeIcons.TopBarSearch: ImageVector
    get() {
        if (_TopBarSearch != null) {
            return _TopBarSearch!!
        }
        _TopBarSearch = ImageVector.Builder(
            name = "We.TopBarSearch",
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
                moveTo(11f, 4f)
                curveTo(7.134f, 4f, 4f, 7.134f, 4f, 11f)
                curveTo(4f, 14.866f, 7.134f, 18f, 11f, 18f)
                curveTo(12.691f, 18f, 14.243f, 17.4f, 15.453f, 16.401f)
                lineTo(19.576f, 20.524f)
                lineTo(20.424f, 19.676f)
                lineTo(16.31f, 15.561f)
                curveTo(17.363f, 14.336f, 18f, 12.743f, 18f, 11f)
                curveTo(18f, 7.134f, 14.866f, 4f, 11f, 4f)
                close()
                moveTo(11f, 5.2f)
                curveTo(7.797f, 5.2f, 5.2f, 7.797f, 5.2f, 11f)
                curveTo(5.2f, 14.203f, 7.797f, 16.8f, 11f, 16.8f)
                curveTo(14.203f, 16.8f, 16.8f, 14.203f, 16.8f, 11f)
                curveTo(16.8f, 7.797f, 14.203f, 5.2f, 11f, 5.2f)
                close()
            }
        }.build()
        return _TopBarSearch!!
    }

@Suppress("ObjectPropertyName")
private var _TopBarSearch: ImageVector? = null
