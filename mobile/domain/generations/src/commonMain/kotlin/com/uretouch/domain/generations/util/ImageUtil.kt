package com.uretouch.domain.generations.util

interface ImageUtil {
    suspend fun saveImage(image: ByteArray): String
    suspend fun deleteImage(path: String)
    suspend fun getImage(path: String): ByteArray
    suspend fun saveGeneration(url: String, prompt: String)
}