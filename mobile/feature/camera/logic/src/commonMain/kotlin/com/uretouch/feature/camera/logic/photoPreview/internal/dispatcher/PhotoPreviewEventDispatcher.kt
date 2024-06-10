package com.uretouch.feature.camera.logic.photoPreview.internal.dispatcher

import com.uretouch.common.core.eventDispatcher.BaseEventDispatcher
import com.uretouch.common.core.eventDispatcher.DispatchEvent

internal class PhotoPreviewEventDispatcher : BaseEventDispatcher<PhotoPreviewEvent>()

internal sealed interface PhotoPreviewEvent : DispatchEvent {
    data class ShowMessage(
        val text: String,
        val isError: Boolean,
    ) : PhotoPreviewEvent
}