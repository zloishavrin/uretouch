package com.uretouch.common.ui.kit.icons

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

internal val Icons.EyeOpen24Regular: ImageVector
    get() {
        if (_eyeOpen24Regular != null) {
            return _eyeOpen24Regular!!
        }
        _eyeOpen24Regular = Builder(
            name = "EyeOpen24Regular", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(12.0f, 7.5f)
                    curveTo(9.5147f, 7.5f, 7.5f, 9.5147f, 7.5f, 12.0f)
                    curveTo(7.5f, 14.4853f, 9.5147f, 16.5f, 12.0f, 16.5f)
                    curveTo(14.4853f, 16.5f, 16.5f, 14.4853f, 16.5f, 12.0f)
                    curveTo(16.5f, 9.5147f, 14.4853f, 7.5f, 12.0f, 7.5f)
                    close()
                    moveTo(9.0f, 12.0f)
                    curveTo(9.0f, 10.3431f, 10.3431f, 9.0f, 12.0f, 9.0f)
                    curveTo(13.6568f, 9.0f, 15.0f, 10.3431f, 15.0f, 12.0f)
                    curveTo(15.0f, 13.6569f, 13.6568f, 15.0f, 12.0f, 15.0f)
                    curveTo(10.3431f, 15.0f, 9.0f, 13.6569f, 9.0f, 12.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(12.0f, 3.75f)
                    curveTo(7.0018f, 3.75f, 2.6974f, 6.7163f, 0.7391f, 10.9835f)
                    curveTo(0.4429f, 11.6288f, 0.4429f, 12.3712f, 0.7391f, 13.0165f)
                    curveTo(2.6974f, 17.2837f, 7.0018f, 20.25f, 12.0f, 20.25f)
                    curveTo(16.9982f, 20.25f, 21.3026f, 17.2837f, 23.2609f, 13.0165f)
                    curveTo(23.5571f, 12.3712f, 23.5571f, 11.6288f, 23.2609f, 10.9835f)
                    curveTo(21.3026f, 6.7163f, 16.9982f, 3.75f, 12.0f, 3.75f)
                    close()
                    moveTo(2.1024f, 11.6091f)
                    curveTo(3.8259f, 7.8536f, 7.611f, 5.25f, 12.0f, 5.25f)
                    curveTo(16.389f, 5.25f, 20.1741f, 7.8536f, 21.8976f, 11.6091f)
                    curveTo(22.0115f, 11.8572f, 22.0115f, 12.1428f, 21.8976f, 12.3909f)
                    curveTo(20.1741f, 16.1464f, 16.389f, 18.75f, 12.0f, 18.75f)
                    curveTo(7.611f, 18.75f, 3.8259f, 16.1464f, 2.1024f, 12.3909f)
                    curveTo(1.9885f, 12.1428f, 1.9885f, 11.8572f, 2.1024f, 11.6091f)
                    close()
                }
            }
        }
            .build()
        return _eyeOpen24Regular!!
    }

private var _eyeOpen24Regular: ImageVector? = null