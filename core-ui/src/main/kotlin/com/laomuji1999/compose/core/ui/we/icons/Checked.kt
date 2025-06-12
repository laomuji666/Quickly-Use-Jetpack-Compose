package com.laomuji1999.compose.core.ui.we.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val WeIcons.Checked: ImageVector
    get() {
        if (_Checked != null) {
            return _Checked!!
        }
        _Checked = ImageVector.Builder(
            name = "WeIcons.Checked",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF07C160))) {
                moveTo(3.561f, 11.314f)
                lineTo(2.5f, 12.374f)
                lineTo(8.157f, 18.031f)
                curveTo(8.547f, 18.422f, 9.181f, 18.422f, 9.571f, 18.031f)
                lineTo(21.238f, 6.364f)
                lineTo(20.178f, 5.303f)
                lineTo(8.864f, 16.617f)
                lineTo(3.561f, 11.314f)
            }
        }.build()

        return _Checked!!
    }

@Suppress("ObjectPropertyName")
private var _Checked: ImageVector? = null
