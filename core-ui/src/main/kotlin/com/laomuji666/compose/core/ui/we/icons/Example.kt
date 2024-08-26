package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WeIcons.Example: ImageVector
    get() {
        if (_Example != null) {
            return _Example!!
        }
        _Example = ImageVector.Builder(
            name = "WeIcons.Example",
            defaultWidth = 64.dp,
            defaultHeight = 64.dp,
            viewportWidth = 1024f,
            viewportHeight = 1024f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(544f, 789.3f)
                horizontalLineToRelative(105.2f)
                arcToRelative(10.7f, 10.7f, 0f, isMoreThanHalf = false, isPositiveArc = false, 10.7f, -10.7f)
                verticalLineToRelative(-0.4f)
                lineTo(656.6f, 684f)
                arcToRelative(32f, 32f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.3f, -27.8f)
                curveTo(750.9f, 603.2f, 800f, 513.8f, 800f, 416f)
                curveToRelative(0f, -159.1f, -128.9f, -288f, -288f, -288f)
                reflectiveCurveTo(224f, 256.9f, 224f, 416f)
                curveToRelative(0f, 97.8f, 49.1f, 187.2f, 129.1f, 240.3f)
                arcToRelative(32f, 32f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.3f, 25.6f)
                lineToRelative(3.3f, 97.2f)
                arcToRelative(10.7f, 10.7f, 0f, isMoreThanHalf = false, isPositiveArc = false, 10.7f, 10.3f)
                horizontalLineTo(480f)
                verticalLineToRelative(-216.6f)
                lineToRelative(-76.6f, -81.4f)
                arcToRelative(32f, 32f, 0f, isMoreThanHalf = false, isPositiveArc = true, 46.6f, -43.9f)
                lineToRelative(63.4f, 67.3f)
                lineToRelative(72.7f, -68.7f)
                arcToRelative(32f, 32f, 0f, isMoreThanHalf = false, isPositiveArc = true, 43.9f, 46.5f)
                lineTo(544f, 573.8f)
                verticalLineToRelative(215.5f)
                close()
                moveTo(864f, 416f)
                arcToRelative(351.5f, 351.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -142.8f, 283.1f)
                lineToRelative(2.6f, 77f)
                arcToRelative(74.7f, 74.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, -74.6f, 77.2f)
                horizontalLineTo(381.4f)
                arcToRelative(74.7f, 74.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, -74.6f, -72.1f)
                lineToRelative(-2.8f, -81.2f)
                arcTo(351.5f, 351.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 160f, 416f)
                curveToRelative(0f, -194.4f, 157.6f, -352f, 352f, -352f)
                reflectiveCurveToRelative(352f, 157.6f, 352f, 352f)
                close()
                moveTo(416f, 960f)
                arcToRelative(32f, 32f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -64f)
                horizontalLineToRelative(192f)
                arcToRelative(32f, 32f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 64f)
                horizontalLineTo(416f)
                close()
            }
        }.build()

        return _Example!!
    }

@Suppress("ObjectPropertyName")
private var _Example: ImageVector? = null
