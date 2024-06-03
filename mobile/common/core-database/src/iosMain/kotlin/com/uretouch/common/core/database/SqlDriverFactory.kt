package com.uretouch.common.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.scope.Scope

internal actual fun Scope.sqlDriverFactory(): SqlDriver {
    return NativeSqliteDriver(
        schema = URetouchDatabase.Schema,
        name = "URetouchDatabase",
    )
}