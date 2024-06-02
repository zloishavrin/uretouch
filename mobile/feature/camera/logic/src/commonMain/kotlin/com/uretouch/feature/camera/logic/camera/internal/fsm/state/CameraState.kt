package com.uretouch.feature.camera.logic.camera.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class CameraState : State {

    data object Initial : CameraState()

    sealed class AsyncWorkerState : CameraState() {
        data class SavingPhoto(
            val image: ByteArray,
        ) : AsyncWorkerState() {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || this::class != other::class) return false

                other as SavingPhoto

                return image.contentEquals(other.image)
            }

            override fun hashCode(): Int {
                return image.contentHashCode()
            }
        }
    }

    sealed class NavigationState : CameraState() {
        data class ToPhotoPreview(
            val sourceState: AsyncWorkerState.SavingPhoto,
            val path: String,
        ) : NavigationState()
    }

    companion object {
        fun initial(): CameraState {
            return Initial
        }
    }
}