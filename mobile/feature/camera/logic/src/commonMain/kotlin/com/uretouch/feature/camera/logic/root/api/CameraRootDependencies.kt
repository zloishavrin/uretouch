package com.uretouch.feature.camera.logic.root.api

import com.uretouch.common.core.util.PlatformOpener
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.domain.generations.util.ImageUtil

class CameraRootDependencies(
    val platformOpener: PlatformOpener,
    val generationsInteractor: GenerationsInteractor,
    val imageUtil: ImageUtil,
)