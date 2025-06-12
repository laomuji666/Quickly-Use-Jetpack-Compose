package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.Loading: ImageVector
    get() {
        if (_Loading != null) {
            return _Loading!!
        }
        _Loading = ImageVector.Builder(
            name = "WeIcons.Loading",
            defaultWidth = 80.dp,
            defaultHeight = 80.dp,
            viewportWidth = 80f,
            viewportHeight = 80f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0x00FFFFFF),
                        1f to Color(0x6CFFFFFF)
                    ),
                    start = Offset(77.635f, 0f),
                    end = Offset(77.635f, 72.447f)
                ),
                fillAlpha = 0.9f,
                strokeAlpha = 0.9f,
                strokeLineWidth = 1f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(40f, 0f)
                curveTo(62.091f, 0f, 80f, 17.909f, 80f, 40f)
                curveTo(80f, 62.091f, 62.091f, 80f, 40f, 80f)
                lineTo(40f, 73f)
                curveTo(58.225f, 73f, 73f, 58.225f, 73f, 40f)
                curveTo(73f, 21.775f, 58.225f, 7f, 40f, 7f)
                lineTo(40f, 0f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFFFFFFF),
                        1f to Color(0x6CFFFFFF)
                    ),
                    start = Offset(40f, 6.939f),
                    end = Offset(40f, 72.503f)
                ),
                fillAlpha = 0.9f,
                strokeAlpha = 0.9f,
                strokeLineWidth = 1f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(40f, 0f)
                lineTo(40f, 7f)
                curveTo(21.775f, 7f, 7f, 21.775f, 7f, 40f)
                curveTo(7f, 58.225f, 21.775f, 73f, 40f, 73f)
                lineTo(40f, 80f)
                curveTo(17.909f, 80f, 0f, 62.091f, 0f, 40f)
                curveTo(0f, 17.909f, 17.909f, 0f, 40f, 0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0.9f,
                strokeAlpha = 0.9f,
                strokeLineWidth = 1f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(40.5f, 3.5f)
                moveToRelative(-3.5f, 0f)
                arcToRelative(3.5f, 3.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 7f, 0f)
                arcToRelative(3.5f, 3.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, -7f, 0f)
            }
        }.build()

        return _Loading!!
    }

@Suppress("ObjectPropertyName")
private var _Loading: ImageVector? = null
