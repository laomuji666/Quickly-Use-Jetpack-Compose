package com.laomuji666.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.Feature: ImageVector
    get() {
        if (_Feature != null) {
            return _Feature!!
        }
        _Feature = ImageVector.Builder(
            name = "WeIcons.Feature",
            defaultWidth = 200.dp,
            defaultHeight = 200.dp,
            viewportWidth = 1024f,
            viewportHeight = 1024f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(230.1f, 18f)
                curveTo(104.7f, 18f, 2.6f, 119.9f, 2.6f, 245.4f)
                reflectiveCurveToRelative(102.1f, 227.5f, 227.5f, 227.5f)
                reflectiveCurveToRelative(227.5f, -102.1f, 227.5f, -227.5f)
                reflectiveCurveToRelative(-102f, -227.5f, -227.5f, -227.5f)
                close()
                moveTo(791.1f, 564.1f)
                curveToRelative(-125.4f, 0f, -227.5f, 102.1f, -227.5f, 227.5f)
                reflectiveCurveToRelative(102.1f, 227.5f, 227.5f, 227.5f)
                curveToRelative(125.5f, 0f, 227.5f, -102.1f, 227.5f, -227.5f)
                reflectiveCurveToRelative(-102f, -227.5f, -227.5f, -227.5f)
                close()
                moveTo(791.1f, 933.1f)
                curveToRelative(-78f, 0f, -141.3f, -63.5f, -141.3f, -141.3f)
                reflectiveCurveToRelative(63.5f, -141.3f, 141.3f, -141.3f)
                curveToRelative(78f, 0f, 141.3f, 63.5f, 141.3f, 141.3f)
                reflectiveCurveToRelative(-63.3f, 141.3f, -141.3f, 141.3f)
                close()
                moveTo(414.5f, 569f)
                lineTo(45.7f, 569f)
                curveToRelative(-23.8f, 0f, -43.1f, 19.2f, -43.1f, 43.1f)
                lineTo(2.6f, 980.9f)
                curveToRelative(0f, 23.8f, 19.2f, 43.1f, 43.1f, 43.1f)
                horizontalLineToRelative(368.8f)
                curveToRelative(23.8f, 0f, 43.1f, -19.2f, 43.1f, -43.1f)
                lineTo(457.6f, 612.1f)
                curveToRelative(0f, -23.8f, -19.2f, -43.1f, -43.1f, -43.1f)
                close()
                moveTo(371.4f, 937.8f)
                lineTo(88.8f, 937.8f)
                lineTo(88.8f, 655.2f)
                horizontalLineToRelative(282.6f)
                lineTo(371.4f, 937.8f)
                close()
                moveTo(776f, 490.9f)
                curveToRelative(11.1f, 0f, 22.1f, -4.2f, 30.4f, -12.6f)
                lineToRelative(202.4f, -202.4f)
                curveToRelative(8f, -8f, 12.6f, -19.1f, 12.6f, -30.4f)
                reflectiveCurveToRelative(-4.6f, -22.4f, -12.6f, -30.4f)
                lineTo(806.4f, 12.6f)
                arcToRelative(43.1f, 43.1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -60.9f, 0f)
                lineTo(543.2f, 215f)
                arcToRelative(43.1f, 43.1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 60.9f)
                lineTo(745.5f, 478.2f)
                curveToRelative(8.5f, 8.3f, 19.5f, 12.6f, 30.4f, 12.6f)
                close()
                moveTo(776f, 104f)
                lineToRelative(141.5f, 141.5f)
                lineToRelative(-141.5f, 141.5f)
                lineTo(634.5f, 245.4f)
                lineToRelative(141.5f, -141.5f)
                close()
            }
        }.build()

        return _Feature!!
    }

@Suppress("ObjectPropertyName")
private var _Feature: ImageVector? = null
