package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.More: ImageVector
    get() {
        if (_More != null) {
            return _More!!
        }
        _More = ImageVector.Builder(
            name = "WeIcons.More",
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
                moveTo(7f, 12f)
                curveTo(7f, 13.104f, 6.104f, 14f, 5f, 14f)
                curveTo(3.896f, 14f, 3f, 13.104f, 3f, 12f)
                curveTo(3f, 10.895f, 3.896f, 10f, 5f, 10f)
                curveTo(6.104f, 10f, 7f, 10.895f, 7f, 12f)
                close()
                moveTo(12f, 10f)
                curveTo(13.104f, 10f, 14f, 10.895f, 14f, 12f)
                curveTo(14f, 13.104f, 13.104f, 14f, 12f, 14f)
                curveTo(10.896f, 14f, 10f, 13.104f, 10f, 12f)
                curveTo(10f, 10.895f, 10.896f, 10f, 12f, 10f)
                close()
                moveTo(21f, 12f)
                curveTo(21f, 10.895f, 20.104f, 10f, 19f, 10f)
                curveTo(17.896f, 10f, 17f, 10.895f, 17f, 12f)
                curveTo(17f, 13.104f, 17.896f, 14f, 19f, 14f)
                curveTo(20.104f, 14f, 21f, 13.104f, 21f, 12f)
                close()
            }
        }.build()

        return _More!!
    }

@Suppress("ObjectPropertyName")
private var _More: ImageVector? = null
