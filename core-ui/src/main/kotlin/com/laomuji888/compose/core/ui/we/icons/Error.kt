package com.laomuji888.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.Error: ImageVector
    get() {
        if (_Error != null) {
            return _Error!!
        }
        _Error = ImageVector.Builder(
            name = "WeIcons.Error",
            defaultWidth = 40.dp,
            defaultHeight = 40.dp,
            viewportWidth = 40f,
            viewportHeight = 40f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0.9f,
                strokeAlpha = 0.9f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(20f, 3.333f)
                curveTo(29.205f, 3.333f, 36.667f, 10.795f, 36.667f, 20f)
                curveTo(36.667f, 29.205f, 29.205f, 36.667f, 20f, 36.667f)
                curveTo(10.795f, 36.667f, 3.333f, 29.205f, 3.333f, 20f)
                curveTo(3.333f, 10.795f, 10.795f, 3.333f, 20f, 3.333f)
                close()
                moveTo(18.91f, 22.887f)
                lineTo(21.086f, 22.887f)
                lineTo(21.268f, 10.226f)
                lineTo(18.728f, 10.226f)
                lineTo(18.91f, 22.887f)
                close()
                moveTo(21.496f, 27.034f)
                curveTo(21.496f, 26.203f, 20.847f, 25.565f, 19.993f, 25.565f)
                curveTo(19.161f, 25.565f, 18.5f, 26.203f, 18.5f, 27.034f)
                curveTo(18.5f, 27.866f, 19.161f, 28.504f, 19.993f, 28.504f)
                curveTo(20.847f, 28.504f, 21.496f, 27.866f, 21.496f, 27.034f)
                close()
            }
        }.build()

        return _Error!!
    }

@Suppress("ObjectPropertyName")
private var _Error: ImageVector? = null
