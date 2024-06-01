package com.uretouch.common.core.environment

import com.uretouch.common.core.BuildConfig

actual object Environment {
    actual val isDebug = BuildConfig.BUILD_TYPE.equals("debug", false)
    actual val isStaging = BuildConfig.BUILD_TYPE.equals("staging", false)
    actual val isMock = BuildConfig.BUILD_TYPE.equals("mock", false)
    actual val isRelease = BuildConfig.BUILD_TYPE.equals("release", false)
}