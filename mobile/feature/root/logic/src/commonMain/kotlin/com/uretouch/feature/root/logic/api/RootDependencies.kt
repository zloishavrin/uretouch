package com.uretouch.feature.root.logic.api

import com.uretouch.common.core.settings.SettingsFactory
import com.uretouch.common.core.util.SettingsOpener
import com.uretouch.domain.generations.util.ImageUtil

class RootDependencies(
    val settingsFactory: SettingsFactory,
    val settingsOpener: SettingsOpener,
    val imageUtil: ImageUtil,
)