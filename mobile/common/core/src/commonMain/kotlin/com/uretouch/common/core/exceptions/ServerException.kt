package com.uretouch.common.core.exceptions

sealed class ServerException : Throwable() {
    class UnknownError(override val message: String) : ServerException()
    class RequestError(override val message: String) : ServerException()
}