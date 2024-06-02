package com.uretouch.app.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityCallbacks(
    private val contextHolder: ContextHolder,
) : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        contextHolder.attachContext(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        contextHolder.detachContext()
    }

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}
}