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

internal val Icons.ArchiveBoxOpenDocs24Solid: ImageVector
    get() {
        if (_archiveBoxOpenDocs24Solid != null) {
            return _archiveBoxOpenDocs24Solid!!
        }
        _archiveBoxOpenDocs24Solid = Builder(
            name = "ArchiveBoxOpenDocs24Solid", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(8.0f, 0.0f)
                    curveTo(7.4477f, 0.0f, 7.0f, 0.4477f, 7.0f, 1.0f)
                    curveTo(7.0f, 1.5523f, 7.4477f, 2.0f, 8.0f, 2.0f)
                    horizontalLineTo(16.0f)
                    curveTo(16.5523f, 2.0f, 17.0f, 1.5523f, 17.0f, 1.0f)
                    curveTo(17.0f, 0.4477f, 16.5523f, 0.0f, 16.0f, 0.0f)
                    horizontalLineTo(8.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(5.0f, 5.5f)
                    curveTo(5.0f, 4.1193f, 6.1193f, 3.0f, 7.5f, 3.0f)
                    horizontalLineTo(16.5f)
                    curveTo(17.8807f, 3.0f, 19.0f, 4.1193f, 19.0f, 5.5f)
                    verticalLineTo(8.0f)
                    horizontalLineTo(20.0f)
                    curveTo(20.5523f, 8.0f, 21.0f, 8.4477f, 21.0f, 9.0f)
                    verticalLineTo(18.5f)
                    curveTo(21.0f, 20.433f, 19.433f, 22.0f, 17.5f, 22.0f)
                    horizontalLineTo(6.5f)
                    curveTo(4.567f, 22.0f, 3.0f, 20.433f, 3.0f, 18.5f)
                    verticalLineTo(9.0f)
                    curveTo(3.0f, 8.4477f, 3.4477f, 8.0f, 4.0f, 8.0f)
                    horizontalLineTo(5.0f)
                    verticalLineTo(5.5f)
                    close()
                    moveTo(17.0f, 5.5f)
                    verticalLineTo(8.0f)
                    horizontalLineTo(7.0f)
                    verticalLineTo(5.5f)
                    curveTo(7.0f, 5.2239f, 7.2239f, 5.0f, 7.5f, 5.0f)
                    horizontalLineTo(16.5f)
                    curveTo(16.7761f, 5.0f, 17.0f, 5.2239f, 17.0f, 5.5f)
                    close()
                    moveTo(9.0f, 12.0f)
                    curveTo(8.4477f, 12.0f, 8.0f, 12.4477f, 8.0f, 13.0f)
                    curveTo(8.0f, 13.5523f, 8.4477f, 14.0f, 9.0f, 14.0f)
                    horizontalLineTo(15.0f)
                    curveTo(15.5523f, 14.0f, 16.0f, 13.5523f, 16.0f, 13.0f)
                    curveTo(16.0f, 12.4477f, 15.5523f, 12.0f, 15.0f, 12.0f)
                    horizontalLineTo(9.0f)
                    close()
                }
            }
        }
            .build()
        return _archiveBoxOpenDocs24Solid!!
    }

private var _archiveBoxOpenDocs24Solid: ImageVector? = null