package com.uretouch.app.lifecycle

import android.app.Activity
import java.lang.ref.WeakReference

class ContextHolder {
    private var weakTask: WeakReference<(Activity) -> Unit>? = null

    private var weakContext: WeakReference<Activity>? = null

    fun attachContext(context: Activity) {
        val task = weakTask?.get()
        if (task != null) {
            task.invoke(context)
            weakTask = null
        }

        weakContext = WeakReference(context)
    }

    fun detachContext() {
        weakContext = null
    }

    fun executeContextTask(task: (Activity) -> Unit) {
        val context = weakContext?.get()
        if (context == null) {
            weakTask = WeakReference(task)
            return
        }

        task.invoke(context)
    }
}