package com.uretouch.common.ui.kit.utils

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import com.uretouch.common.ui.kit.utils.BottomNavigationState.Companion.DefaultAnimationDuration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Suppress("CompositionLocalAllowlist")
val LocalBottomNavigationState = staticCompositionLocalOf<BottomNavigationState> {
    error("Bottom Navigation State is not provided")
}

@Stable
data class BottomNavigationVisibleState(
    val isVisible: Boolean,
    val animateDuration: Long = DefaultAnimationDuration,
)

@Stable
class BottomNavigationState(initialVisible: Boolean) {
    private val _state: MutableStateFlow<BottomNavigationVisibleState> = MutableStateFlow(
        BottomNavigationVisibleState(isVisible = initialVisible)
    )

    val state: StateFlow<BottomNavigationVisibleState> = _state.asStateFlow()
    fun showBottomNavigation(animateDuration: Long = DefaultAnimationDuration) {
        _state.value = BottomNavigationVisibleState(isVisible = true, animateDuration = animateDuration)
    }

    fun hideBottomNavigation(animateDuration: Long = DefaultAnimationDuration) {
        _state.value = BottomNavigationVisibleState(isVisible = false, animateDuration = animateDuration)
    }

    companion object {
        const val DefaultAnimationDuration = 300L
    }
}