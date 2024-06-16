package ar.edu.unlam.mobile.scaffolding.data.di

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

object ImageCacheUtil {
    suspend fun cacheImage(context: Context, url: String): String? {
        return try {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(url)
                .build()

            val result = (loader.execute(request) as SuccessResult).drawable
            val bitmap = result.toBitmap()
            val file = File(context.cacheDir, "image_${System.currentTimeMillis()}.png")
            val fos = withContext(Dispatchers.IO) {
                FileOutputStream(file)
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            withContext(Dispatchers.IO) {
                fos.flush()
            }
            withContext(Dispatchers.IO) {
                fos.close()
            }
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
