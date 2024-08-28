package com.laomuji666.compose.core.ui.we2.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.we.icons.WeIcons
import kotlin.Suppress

val WeIcons.Select: ImageVector
    get() {
        if (_Select != null) {
            return _Select!!
        }
        _Select = ImageVector.Builder(
            name = "WeIcons.Select",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF07C160))) {
                moveTo(24f, 12.148f)
                curveTo(24f, 5.372f, 18.628f, 0f, 11.852f, 0f)
                curveTo(5.372f, 0f, 0f, 5.372f, 0f, 12.148f)
                curveTo(0f, 18.628f, 5.372f, 24f, 11.852f, 24f)
                curveTo(18.628f, 24f, 24f, 18.628f, 24f, 12.148f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(7.061f, 12.01f)
                lineTo(6f, 13.071f)
                lineTo(9.657f, 16.728f)
                curveTo(10.047f, 17.118f, 10.681f, 17.118f, 11.071f, 16.728f)
                lineTo(18.738f, 9.061f)
                lineTo(17.678f, 8f)
                lineTo(10.364f, 15.314f)
                lineTo(7.061f, 12.01f)
            }
        }.build()

        return _Select!!
    }

@Suppress("ObjectPropertyName")
private var _Select: ImageVector? = null
