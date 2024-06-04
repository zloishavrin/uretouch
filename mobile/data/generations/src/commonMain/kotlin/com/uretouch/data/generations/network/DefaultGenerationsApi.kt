package com.uretouch.data.generations.network

import com.uretouch.data.generations.network.model.ApiGeneration
import com.uretouch.data.generations.network.model.ApiGenerationMode
import com.uretouch.data.generations.network.model.ApiGenerationRequest
import com.uretouch.data.generations.network.model.ApiGenerationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.append
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.path
import io.ktor.utils.io.core.writeFully

internal class DefaultGenerationsApi(
    private val httpClient: HttpClient,
) : GenerationsApi {
    override suspend fun getGenerationsHistory(): List<ApiGeneration> {
        return httpClient.get {
            url {
                path("/api/user/history")
            }
        }.body()
    }

    override suspend fun getGeneration(id: String): ApiGeneration {
        return httpClient.get {
            url {
                path("/api/user/generation/${id}")
            }
        }.body()
    }

    override suspend fun createGeneration(request: ApiGenerationRequest): ApiGenerationResponse {
        return httpClient.post {
            url {
                path("/api/generation/private")
            }
            val size = request.image.size.toLong()
            setBody(
                MultiPartFormDataContent(
                    parts = formData {
                        append("prompt", request.prompt)
                        request.mode?.let { append("mode", it) }
                        append(
                            key = "image",
                            filename = "photo.png",
                            contentType = ContentType.parse("image/png"),
                            size = size,
                        ) {
                            writeFully(request.image)
                        }
                    }
                )
            )
        }.body()
    }

    override suspend fun getGenerationModes(): List<ApiGenerationMode> {
        return httpClient.get {
            url {
                path("/api/generation/private/mods")
            }
        }.body()
    }
}