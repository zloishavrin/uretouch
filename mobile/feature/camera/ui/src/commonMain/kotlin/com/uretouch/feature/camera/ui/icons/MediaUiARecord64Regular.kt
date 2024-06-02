package com.uretouch.feature.camera.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val Icons.MediaUiARecord64Regular: ImageVector
    get() {
        if (_mediaUiARecord64Regular != null) {
            return _mediaUiARecord64Regular!!
        }
        _mediaUiARecord64Regular = Builder(
            name = "MediaUiARecord64Regular", defaultWidth = 64.0.dp,
            defaultHeight = 64.0.dp, viewportWidth = 64.0f, viewportHeight = 64.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(8.0f, 32.0f)
                    curveTo(8.0f, 18.7452f, 18.7452f, 8.0f, 32.0f, 8.0f)
                    curveTo(45.2548f, 8.0f, 56.0f, 18.7452f, 56.0f, 32.0f)
                    curveTo(56.0f, 45.2548f, 45.2548f, 56.0f, 32.0f, 56.0f)
                    curveTo(18.7452f, 56.0f, 8.0f, 45.2548f, 8.0f, 32.0f)
                    close()
                    moveTo(32.0f, 11.0f)
                    curveTo(20.402f, 11.0f, 11.0f, 20.402f, 11.0f, 32.0f)
                    curveTo(11.0f, 43.598f, 20.402f, 53.0f, 32.0f, 53.0f)
                    curveTo(43.598f, 53.0f, 53.0f, 43.598f, 53.0f, 32.0f)
                    curveTo(53.0f, 20.402f, 43.598f, 11.0f, 32.0f, 11.0f)
                    close()
                }
            }
        }
            .build()
        return _mediaUiARecord64Regular!!
    }

private var _mediaUiARecord64Regular: ImageVector? = null