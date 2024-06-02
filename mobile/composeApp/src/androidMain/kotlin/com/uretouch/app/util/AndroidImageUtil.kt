package com.uretouch.app.util

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.uretouch.domain.generations.util.ImageUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class AndroidImageUtil(
    private val context: Context,
) : ImageUtil {

    override suspend fun saveImage(image: ByteArray): String = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/URetouch")
            }
            val insertFileUri = requireNotNull(context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues))
            context.contentResolver.openOutputStream(insertFileUri)
                .use { outputStream ->
                    requireNotNull(outputStream).buffered(image.size).write(image)
                    continuation.resume(insertFileUri.toString())
                }
        }
    }

    override suspend fun deleteImage(path: String) = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            context.contentResolver.delete(Uri.parse(path), null, null)
            continuation.resume(Unit)
        }
    }

    override suspend fun getImage(path: String): ByteArray = withContext(Dispatchers.IO) {
        requireNotNull(context.contentResolver.openInputStream(Uri.parse(path))).use { it.buffered().readBytes() }
    }
}