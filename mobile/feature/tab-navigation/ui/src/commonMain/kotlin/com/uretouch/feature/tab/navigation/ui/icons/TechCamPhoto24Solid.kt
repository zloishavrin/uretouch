package com.uretouch.feature.tab.navigation.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val Icons.TechCamPhoto24Solid: ImageVector
    get() {
        if (_techCamPhoto24Solid != null) {
            return _techCamPhoto24Solid!!
        }
        _techCamPhoto24Solid = Builder(
            name = "TechCamPhoto24Solid", defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(8.6281f, 12.5038f)
                    curveTo(8.6281f, 10.6393f, 10.1395f, 9.1279f, 12.0039f, 9.1279f)
                    curveTo(13.8683f, 9.1279f, 15.3797f, 10.6393f, 15.3797f, 12.5038f)
                    curveTo(15.3797f, 14.3682f, 13.8683f, 15.8796f, 12.0039f, 15.8796f)
                    curveTo(10.1395f, 15.8796f, 8.6281f, 14.3682f, 8.6281f, 12.5038f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(6.0f, 5.0f)
                    curveTo(3.7909f, 5.0f, 2.0f, 6.7909f, 2.0f, 9.0f)
                    verticalLineTo(17.0f)
                    curveTo(2.0f, 19.2091f, 3.7909f, 21.0f, 6.0f, 21.0f)
                    horizontalLineTo(18.0f)
                    curveTo(20.2091f, 21.0f, 22.0f, 19.2091f, 22.0f, 17.0f)
                    verticalLineTo(9.0f)
                    curveTo(22.0f, 6.7909f, 20.2091f, 5.0f, 18.0f, 5.0f)
                    horizontalLineTo(17.0f)
                    lineTo(16.0528f, 3.1056f)
                    curveTo(15.714f, 2.428f, 15.0215f, 2.0f, 14.2639f, 2.0f)
                    horizontalLineTo(9.7361f)
                    curveTo(8.9785f, 2.0f, 8.286f, 2.428f, 7.9472f, 3.1056f)
                    lineTo(7.0f, 5.0f)
                    horizontalLineTo(6.0f)
                    close()
                    moveTo(12.0039f, 7.3779f)
                    curveTo(9.173f, 7.3779f, 6.8781f, 9.6728f, 6.8781f, 12.5038f)
                    curveTo(6.8781f, 15.3347f, 9.173f, 17.6296f, 12.0039f, 17.6296f)
                    curveTo(14.8348f, 17.6296f, 17.1297f, 15.3347f, 17.1297f, 12.5038f)
                    curveTo(17.1297f, 9.6728f, 14.8348f, 7.3779f, 12.0039f, 7.3779f)
                    close()
                }
            }
        }
            .build()
        return _techCamPhoto24Solid!!
    }

private var _techCamPhoto24Solid: ImageVector? = null