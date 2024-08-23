package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.TopBarMenu: ImageVector
    get() {
        if (_TopBarMenu != null) {
            return _TopBarMenu!!
        }
        _TopBarMenu = ImageVector.Builder(
            name = "We.TopBarMenu",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5f, 10.25f)
                curveTo(4.034f, 10.25f, 3.25f, 11.033f, 3.25f, 12f)
                curveTo(3.25f, 12.966f, 4.034f, 13.75f, 5f, 13.75f)
                curveTo(5.966f, 13.75f, 6.75f, 12.966f, 6.75f, 12f)
                curveTo(6.75f, 11.033f, 5.966f, 10.25f, 5f, 10.25f)
                close()
                moveTo(10.25f, 12f)
                curveTo(10.25f, 12.966f, 11.034f, 13.75f, 12f, 13.75f)
                curveTo(12.966f, 13.75f, 13.75f, 12.966f, 13.75f, 12f)
                curveTo(13.75f, 11.033f, 12.966f, 10.25f, 12f, 10.25f)
                curveTo(11.034f, 10.25f, 10.25f, 11.033f, 10.25f, 12f)
                close()
                moveTo(19f, 13.75f)
                curveTo(18.034f, 13.75f, 17.25f, 12.966f, 17.25f, 12f)
                curveTo(17.25f, 11.033f, 18.034f, 10.25f, 19f, 10.25f)
                curveTo(19.966f, 10.25f, 20.75f, 11.033f, 20.75f, 12f)
                curveTo(20.75f, 12.966f, 19.966f, 13.75f, 19f, 13.75f)
                close()
            }
        }.build()

        return _TopBarMenu!!
    }

@Suppress("ObjectPropertyName")
private var _TopBarMenu: ImageVector? = null
