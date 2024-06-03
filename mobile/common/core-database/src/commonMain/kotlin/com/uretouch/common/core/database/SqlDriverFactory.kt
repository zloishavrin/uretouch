package com.uretouch.common.core.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

internal expect fun Scope.sqlDriverFactory(): SqlDriver
internal fun createDatabase(driver: SqlDriver): URetouchDatabase {
    return URetouchDatabase(driver = driver)
}