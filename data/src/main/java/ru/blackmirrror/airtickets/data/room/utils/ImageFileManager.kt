package ru.blackmirrror.airtickets.data.room.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

object ImageFileManager {
    suspend fun saveImageToStorage(
        context: Context,
        drawable: Drawable?,
        folderName: String,
        dispatcher: CoroutineDispatcher
    ): String? {
        if (drawable == null) return null

        val fileName = "image_${System.currentTimeMillis()}.png"
        val outputStream: FileOutputStream
        var filePath: String? = null

        try {
            val folderPath = File(context.filesDir, folderName)
            if (!folderPath.exists()) {
                folderPath.mkdirs()
            }
            val file = File(folderPath, fileName)
            outputStream = withContext(dispatcher) {
                FileOutputStream(file)
            }
            val bitmap = (drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

            withContext(dispatcher) {
                outputStream.close()
            }

            filePath = "$folderName/$fileName"
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return filePath
    }


    suspend fun loadImageFromStorage(
        context: Context,
        imagePath: String? = null,
        dispatcher: CoroutineDispatcher
    ): Drawable? {
        if (imagePath.isNullOrEmpty()) return null
        try {
            val file = File(context.filesDir, imagePath)

            if (file.exists()) {
                val inputStream = withContext(dispatcher) {
                    FileInputStream(file)
                }
                val bitmap = BitmapFactory.decodeStream(inputStream)

                withContext(dispatcher) {
                    inputStream.close()
                }

                return BitmapDrawable(context.resources, bitmap)
            } else {
                return null
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


    suspend fun deleteFolder(
        context: Context, folderName: String,
        dispatcher: CoroutineDispatcher
    ): Boolean {
        return withContext(dispatcher) {
            try {
                val folderPath = File(context.filesDir, folderName)
                if (folderPath.exists()) {
                    folderPath.deleteRecursively()
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

}