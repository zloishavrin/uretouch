package com.uretouch.common.core.environment

import platform.Foundation.NSBundle

actual object Environment {
    actual val isDebug: Boolean = isOneOfConfigurationsEnabled(arrayOf("Development.Debug", "Production.Debug"))
    actual val isRelease: Boolean = isOneOfConfigurationsEnabled(arrayOf("Development.Release", "Production.Release"))
    actual val isStaging: Boolean = isOneOfConfigurationsEnabled(arrayOf("Staging"))
    actual val isMock: Boolean = isOneOfConfigurationsEnabled(arrayOf("Mock"))
}

private fun isOneOfConfigurationsEnabled(configurations: Array<String>): Boolean {
    val currentConfiguration = NSBundle.mainBundle.objectForInfoDictionaryKey("Configuration") as String
    return configurations.contains(currentConfiguration)
}