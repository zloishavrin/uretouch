package com.uretouch.common.core.network.provider

import android.annotation.SuppressLint
import com.uretouch.common.core.environment.Environment
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

internal actual class HttpClientEngineProvider {
    actual fun get(): HttpClientEngine {
        return OkHttp.create {
            config {
                if (Environment.isRelease.not()) {
                    // For intercepting requests in debug and staging
                    val trustManager = createDevelopTrustManager()
                    val sslSocketFactory = createDevelopSslSocketFactory(trustManager)
                    sslSocketFactory(sslSocketFactory, trustManager)
                }
            }
        }
    }

    @SuppressLint("TrustAllX509TrustManager")
    private fun createDevelopTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                // don't need implementation
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                // don't need implementation
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }

    private fun createDevelopSslSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustManager), SecureRandom())

        return sslContext.socketFactory
    }
}