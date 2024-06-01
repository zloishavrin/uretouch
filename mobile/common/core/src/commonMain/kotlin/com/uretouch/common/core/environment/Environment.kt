package com.uretouch.common.core.environment

expect object Environment {
    val isDebug: Boolean
    val isStaging: Boolean
    val isMock: Boolean
    val isRelease: Boolean
}