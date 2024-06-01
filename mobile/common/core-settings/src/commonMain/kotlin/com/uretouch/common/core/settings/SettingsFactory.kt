package com.uretouch.common.core.settings

import com.russhwolf.settings.Settings

expect class SettingsFactory {
    fun createSettings(): Settings
}