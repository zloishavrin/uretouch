package com.uretouch.feature.camera.logic.root.api

import com.uretouch.common.core.util.SettingsOpener
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.domain.generations.util.ImageUtil

class CameraRootDependencies(
    val settingsOpener: SettingsOpener,
    val generationsInteractor: GenerationsInteractor,
    val imageUtil: ImageUtil,
)