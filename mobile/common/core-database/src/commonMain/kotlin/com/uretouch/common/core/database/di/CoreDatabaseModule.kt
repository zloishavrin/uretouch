package com.uretouch.common.core.database.di

import com.uretouch.common.core.database.createDatabase
import com.uretouch.common.core.database.sqlDriverFactory
import org.koin.dsl.module

object CoreDatabaseModule {
    val module = module {
        factory { sqlDriverFactory() }
        single { createDatabase(driver = get()) }
    }
}