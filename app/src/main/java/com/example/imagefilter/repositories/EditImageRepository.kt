package com.example.imagefilter.repositories

import android.graphics.Bitmap
import android.net.Uri

interface EditImageRepository {

    suspend fun prepareImagePreview(imageUri: Uri) : Bitmap?
}