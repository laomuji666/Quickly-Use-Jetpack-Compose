package com.laomuji666.compose.feature.chat.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlin.Suppress

val WeIcons.Message: ImageVector
    get() {
        if (_Message != null) {
            return _Message!!
        }
        _Message = ImageVector.Builder(
            name = "WeIcons.Message",
            defaultWidth = 64.dp,
            defaultHeight = 64.dp,
            viewportWidth = 1024f,
            viewportHeight = 1024f
        ).apply {
            path(fill = SolidColor(Color(0xFF07C160))) {
                moveTo(512f, 896f)
                horizontalLineToRelative(-85.3f)
                lineTo(192f, 1024f)
                lineTo(192f, 789.3f)
                curveTo(75f, 707.2f, 0f, 589.4f, 0f, 448f)
                curveTo(0f, 200.6f, 229.2f, 0f, 512f, 0f)
                reflectiveCurveToRelative(512f, 200.6f, 512f, 448f)
                curveToRelative(0f, 247.4f, -229.2f, 448f, -512f, 448f)
                close()
                moveTo(512f, 64f)
                curveToRelative(-247.4f, 0f, -448f, 171.9f, -448f, 384f)
                curveToRelative(0f, 130.1f, 76.5f, 250.5f, 192f, 320f)
                verticalLineToRelative(142.2f)
                lineToRelative(170.6f, -78.2f)
                horizontalLineToRelative(85.3f)
                curveToRelative(247.4f, 0f, 448f, -171.9f, 448f, -384f)
                curveToRelative(0f, -212.1f, -200.6f, -384f, -448f, -384f)
                close()
                moveTo(768f, 512f)
                curveToRelative(-35.4f, 0f, -64f, -28.7f, -64f, -64f)
                reflectiveCurveToRelative(28.7f, -64f, 64f, -64f)
                curveToRelative(35.3f, 0f, 64f, 28.7f, 64f, 64f)
                reflectiveCurveToRelative(-28.7f, 64f, -64f, 64f)
                close()
                moveTo(512f, 512f)
                curveToRelative(-35.4f, 0f, -64f, -28.7f, -64f, -64f)
                reflectiveCurveToRelative(28.6f, -64f, 64f, -64f)
                curveToRelative(35.4f, 0f, 64f, 28.7f, 64f, 64f)
                reflectiveCurveToRelative(-28.7f, 64f, -64f, 64f)
                close()
                moveTo(256f, 512f)
                curveToRelative(-35.3f, 0f, -64f, -28.7f, -64f, -64f)
                reflectiveCurveToRelative(28.7f, -64f, 64f, -64f)
                curveToRelative(35.4f, 0f, 64f, 28.7f, 64f, 64f)
                reflectiveCurveToRelative(-28.7f, 64f, -64f, 64f)
                close()
            }
        }.build()

        return _Message!!
    }

@Suppress("ObjectPropertyName")
private var _Message: ImageVector? = null
