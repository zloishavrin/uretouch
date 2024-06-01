package com.uretouch.common.core.settings

import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(private val sharedPreferences: SharedPreferences) {
    actual fun createSettings(): Settings {
        return SharedPreferencesSettings(sharedPreferences)
    }
}