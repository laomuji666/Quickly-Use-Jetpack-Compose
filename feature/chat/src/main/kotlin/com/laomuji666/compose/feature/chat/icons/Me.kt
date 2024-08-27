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
                moveTo(512f, 35f)
                curveToRelative(111.6f, 0f, 202.1f, 90.5f, 202.1f, 202f)
                verticalLineToRelative(86.4f)
                curveToRelative(0f, 57.5f, -30.3f, 140f, -67.3f, 183.9f)
                lineToRelative(-18.3f, 21.6f)
                curveToRelative(-29.8f, 35.3f, -20.5f, 80.2f, 21.2f, 100.5f)
                lineToRelative(286f, 139.4f)
                curveTo(966.8f, 784f, 992f, 824.1f, 992f, 858.9f)
                lineTo(992f, 893.5f)
                curveToRelative(0f, 27.9f, -22.6f, 50.5f, -50.5f, 50.5f)
                lineTo(82.5f, 944f)
                curveTo(54.6f, 944f, 32f, 921.4f, 32f, 893.5f)
                verticalLineToRelative(-34.6f)
                curveToRelative(0f, -34.6f, 25.2f, -74.9f, 56.3f, -90.1f)
                lineToRelative(286f, -139.5f)
                curveToRelative(41.5f, -20.2f, 51.1f, -65f, 21.2f, -100.5f)
                lineToRelative(-18.3f, -21.6f)
                curveToRelative(-37.2f, -44f, -67.3f, -126.3f, -67.3f, -183.9f)
                verticalLineToRelative(-86.3f)
                curveTo(309.9f, 125.4f, 400.6f, 35f, 512f, 35f)
                close()
                moveTo(512f, 99f)
                curveToRelative(-75.4f, 0f, -136.9f, 60.6f, -138.1f, 135.7f)
                lineToRelative(-0f, 2.3f)
                verticalLineToRelative(86.3f)
                curveToRelative(0f, 42f, 24.3f, 108.7f, 51.4f, 141.6f)
                lineToRelative(0.8f, 1f)
                lineToRelative(18.3f, 21.6f)
                curveToRelative(56.2f, 66.6f, 37.4f, 158.9f, -39.7f, 198.2f)
                lineToRelative(-2.4f, 1.2f)
                lineToRelative(-286f, 139.5f)
                curveToRelative(-3.6f, 1.8f, -7.6f, 5.7f, -11.1f, 10.5f)
                lineToRelative(-0.5f, 0.8f)
                curveToRelative(-0.2f, 0.3f, -0.4f, 0.5f, -0.5f, 0.8f)
                lineToRelative(-0.5f, 0.8f)
                arcToRelative(61.3f, 61.3f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.1f, 5.2f)
                lineToRelative(-0.4f, 0.8f)
                curveToRelative(-2.4f, 4.8f, -4f, 9.6f, -4.1f, 13.4f)
                lineTo(96f, 880f)
                horizontalLineToRelative(832f)
                verticalLineToRelative(-21.1f)
                curveToRelative(0f, -5.2f, -2.9f, -12.5f, -7f, -18.9f)
                lineToRelative(-0.5f, -0.8f)
                curveToRelative(-1.7f, -2.6f, -3.7f, -5.1f, -5.6f, -7.2f)
                lineToRelative(-0.6f, -0.6f)
                curveToRelative(-2.1f, -2.1f, -4.3f, -3.9f, -6.3f, -4.9f)
                lineToRelative(-0.3f, -0.1f)
                lineToRelative(-286f, -139.4f)
                curveToRelative(-78.3f, -38.2f, -98.3f, -130.3f, -43.7f, -197.3f)
                lineToRelative(1.7f, -2f)
                lineToRelative(18.3f, -21.6f)
                curveToRelative(27.1f, -32.1f, 51.7f, -98.9f, 52.2f, -141.3f)
                lineToRelative(0f, -1.3f)
                lineTo(650.1f, 237f)
                curveTo(650.1f, 160.9f, 588.2f, 99f, 512f, 99f)
                close()
            }
        }.build()

        return _Me!!
    }

@Suppress("ObjectPropertyName")
private var _Me: ImageVector? = null
