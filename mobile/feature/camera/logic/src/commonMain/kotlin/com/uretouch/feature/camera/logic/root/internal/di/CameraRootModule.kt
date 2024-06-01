package com.uretouch.feature.camera.logic.root.internal.di

import com.uretouch.feature.camera.logic.root.api.CameraRootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal object CameraRootModule {
    fun create(dependencies: CameraRootDependencies): List<Module> {
        return listOf(
            module {

            }
        )
    }
}