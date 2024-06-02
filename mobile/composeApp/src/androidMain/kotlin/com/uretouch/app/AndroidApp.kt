package com.uretouch.app

import android.app.Application
import com.uretouch.app.di.PlatformModule
import com.uretouch.app.lifecycle.ActivityCallbacks
import com.uretouch.common.core.environment.Environment
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
        initActivityCallbacks()
        INSTANCE = this
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AndroidApp)
            modules(PlatformModule.module)
        }
    }

    private fun initActivityCallbacks() {
        registerActivityLifecycleCallbacks(ActivityCallbacks(contextHolder = get()))
    }

    private fun initLogger() {
        if (Environment.isRelease) {
//            Napier.base(ReleaseAntilog())
        } else {
            Napier.base(DebugAntilog())
        }
    }
}