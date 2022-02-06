package com.example.imagefilter.viewmodels

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagefilter.repositories.EditImageRepository
import com.example.imagefilter.utilities.Coroutines

class EditImageViewModel(private val editImageRepository: EditImageRepository) : ViewModel() {

    private val imagePreviewDataState = MutableLiveData<ImagePreviewDataState>()
    val uiState: LiveData<ImagePreviewDataState> get() = imagePreviewDataState

    fun prepareImagePreview(imageUri: Uri) {
        Coroutines.io {
            kotlin.runCatching {
                emitImagePreviewUiState(isLoading = true)
                editImageRepository.prepareImagePreview(imageUri)
            }.onSuccess { bitmap ->
                if (bitmap != null) {
                    emitImagePreviewUiState(bitmap = bitmap)
                } else {
                    emitImagePreviewUiState(error = "Unable to prepare image preview")
                }
            }.onFailure {
                emitImagePreviewUiState(error = it.message.toString())
            }
        }
    }

    private fun emitImagePreviewUiState(
        isLoading: Boolean = false,
        bitmap: Bitmap? = null,
        error: String? = null
    ) {
        val dataState = ImagePreviewDataState(isLoading, bitmap, error)
        imagePreviewDataState.value = dataState
    }


    data class ImagePreviewDataState(
        val isLoading: Boolean,
        val bitmap: Bitmap?,
        val error: String?
    )
}