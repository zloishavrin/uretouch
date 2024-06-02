package com.uretouch.app.util

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.provider.Settings
import com.uretouch.common.core.util.SettingsOpener

internal class AndroidSettingsOpener(
    private val application: Application,
) : SettingsOpener {

    override fun settingsOpen() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", application.packageName, null),
        ).also {
            it.addFlags(FLAG_ACTIVITY_NEW_TASK)
        }
        application.startActivity(intent)
    }
}