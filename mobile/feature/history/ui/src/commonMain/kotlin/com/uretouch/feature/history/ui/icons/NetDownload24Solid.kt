package com.uretouch.feature.history.ui.icons

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

internal val Icons.NetDownload24Solid: ImageVector
    get() {
        if (_netDownload24Solid != null) {
            return _netDownload24Solid!!
        }
        _netDownload24Solid = Builder(
            name = "NetDownload24Solid", defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(12.0f, 1.0f)
                    curveTo(12.8284f, 1.0f, 13.5f, 1.6716f, 13.5f, 2.5f)
                    verticalLineTo(13.9259f)
                    lineTo(17.7534f, 10.145f)
                    curveTo(18.3726f, 9.5947f, 19.3207f, 9.6504f, 19.8711f, 10.2696f)
                    curveTo(20.4214f, 10.8888f, 20.3657f, 11.8369f, 19.7465f, 12.3873f)
                    lineTo(14.4913f, 17.0585f)
                    curveTo(13.0705f, 18.3215f, 10.9294f, 18.3215f, 9.5086f, 17.0585f)
                    lineTo(4.2534f, 12.3873f)
                    curveTo(3.6342f, 11.8369f, 3.5785f, 10.8888f, 4.1288f, 10.2696f)
                    curveTo(4.6792f, 9.6504f, 5.6273f, 9.5947f, 6.2465f, 10.145f)
                    lineTo(10.5f, 13.9259f)
                    verticalLineTo(2.5f)
                    curveTo(10.5f, 1.6716f, 11.1715f, 1.0f, 12.0f, 1.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(2.0f, 21.5f)
                    curveTo(2.0f, 20.6716f, 2.6716f, 20.0f, 3.5f, 20.0f)
                    horizontalLineTo(20.5f)
                    curveTo(21.3284f, 20.0f, 22.0f, 20.6716f, 22.0f, 21.5f)
                    curveTo(22.0f, 22.3284f, 21.3284f, 23.0f, 20.5f, 23.0f)
                    horizontalLineTo(3.5f)
                    curveTo(2.6716f, 23.0f, 2.0f, 22.3284f, 2.0f, 21.5f)
                    close()
                }
            }
        }
            .build()
        return _netDownload24Solid!!
    }

private var _netDownload24Solid: ImageVector? = null