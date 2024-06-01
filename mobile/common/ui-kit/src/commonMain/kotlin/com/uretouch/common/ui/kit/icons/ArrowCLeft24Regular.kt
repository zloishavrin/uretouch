package com.uretouch.common.ui.kit.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.ArrowCLeft24Regular: ImageVector
    get() {
        if (_arrowCLeft24Regular != null) {
            return _arrowCLeft24Regular!!
        }
        _arrowCLeft24Regular = Builder(
            name = "ArrowCLeft24Regular", defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(16.1185f, 20.6166f)
                    curveTo(15.7768f, 20.9583f, 15.2228f, 20.9583f, 14.8811f, 20.6166f)
                    lineTo(8.4744f, 14.2098f)
                    curveTo(7.254f, 12.9895f, 7.254f, 11.0108f, 8.4744f, 9.7904f)
                    lineTo(14.8811f, 3.3837f)
                    curveTo(15.2228f, 3.042f, 15.7768f, 3.042f, 16.1185f, 3.3837f)
                    curveTo(16.4602f, 3.7254f, 16.4602f, 4.2794f, 16.1185f, 4.6212f)
                    lineTo(9.7118f, 11.0279f)
                    curveTo(9.1748f, 11.5648f, 9.1748f, 12.4354f, 9.7118f, 12.9724f)
                    lineTo(16.1185f, 19.3791f)
                    curveTo(16.4602f, 19.7208f, 16.4602f, 20.2748f, 16.1185f, 20.6166f)
                    close()
                }
            }
        }
            .build()
        return _arrowCLeft24Regular!!
    }

private var _arrowCLeft24Regular: ImageVector? = null