package com.laomuji666.compose.feature.chat.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlin.Suppress

val WeIcons.Me: ImageVector
    get() {
        if (_Me != null) {
            return _Me!!
        }
        _Me = ImageVector.Builder(
            name = "WeIcons.Me",
            defaultWidth = 64.dp,
            defaultHeight = 64.dp,
            viewportWidth = 1024f,
            viewportHeight = 1024f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(640f, 473.6f)
                curveToRelative(57.6f, -38.4f, 96f, -108.8f, 96f, -185.6f)
                curveTo(736f, 166.4f, 633.6f, 64f, 512f, 64f)
                reflectiveCurveTo(288f, 166.4f, 288f, 288f)
                curveToRelative(0f, 76.8f, 38.4f, 140.8f, 96f, 185.6f)
                curveToRelative(-147.2f, 57.6f, -256f, 211.2f, -256f, 390.4f)
                curveToRelative(0f, 19.2f, 0f, 44.8f, 6.4f, 70.4f)
                curveToRelative(0f, 12.8f, 12.8f, 25.6f, 32f, 25.6f)
                horizontalLineToRelative(691.2f)
                curveToRelative(12.8f, 0f, 32f, -12.8f, 32f, -25.6f)
                curveToRelative(6.4f, -25.6f, 6.4f, -44.8f, 6.4f, -70.4f)
                curveToRelative(0f, -179.2f, -108.8f, -332.8f, -256f, -390.4f)
                close()
                moveTo(352f, 288f)
                curveTo(352f, 198.4f, 422.4f, 128f, 512f, 128f)
                reflectiveCurveToRelative(160f, 70.4f, 160f, 160f)
                reflectiveCurveTo(601.6f, 448f, 512f, 448f)
                reflectiveCurveTo(352f, 377.6f, 352f, 288f)
                close()
                moveTo(832f, 896f)
                horizontalLineTo(192f)
                verticalLineToRelative(-32f)
                curveTo(192f, 672f, 332.8f, 512f, 512f, 512f)
                reflectiveCurveToRelative(320f, 160f, 320f, 352f)
                verticalLineToRelative(32f)
                close()
            }
        }.build()

        return _Me!!
    }

@Suppress("ObjectPropertyName")
private var _Me: ImageVector? = null
