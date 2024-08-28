package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.DiscoverSelect: ImageVector
    get() {
        if (_DiscoverSelect != null) {
            return _DiscoverSelect!!
        }
        _DiscoverSelect = ImageVector.Builder(
            name = "WeIcons.DiscoverSelect",
            defaultWidth = 29.dp,
            defaultHeight = 28.dp,
            viewportWidth = 29f,
            viewportHeight = 28f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF39CD80)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(26.042f, 14f)
                curveTo(26.042f, 20.443f, 20.818f, 25.667f, 14.375f, 25.667f)
                curveTo(7.932f, 25.667f, 2.708f, 20.443f, 2.708f, 14f)
                curveTo(2.708f, 7.557f, 7.932f, 2.333f, 14.375f, 2.333f)
                curveTo(20.818f, 2.333f, 26.042f, 7.557f, 26.042f, 14f)
                close()
                moveTo(9.296f, 19.079f)
                lineTo(12.746f, 12.371f)
                lineTo(19.454f, 8.921f)
                lineTo(16.004f, 15.629f)
                lineTo(9.296f, 19.079f)
                close()
            }
        }.build()

        return _DiscoverSelect!!
    }

@Suppress("ObjectPropertyName")
private var _DiscoverSelect: ImageVector? = null
