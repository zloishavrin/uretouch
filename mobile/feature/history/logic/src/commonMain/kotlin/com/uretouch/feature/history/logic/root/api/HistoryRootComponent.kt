package com.uretouch.feature.history.logic.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.history.logic.generationDetail.api.GenerationDetailComponent
import com.uretouch.feature.history.logic.history.api.HistoryComponent

interface HistoryRootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class History(val component: HistoryComponent) : Child()
        class GenerationDetail(val component: GenerationDetailComponent) : Child()
    }
}