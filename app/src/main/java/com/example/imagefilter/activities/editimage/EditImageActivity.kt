package com.example.imagefilter.activities.editimage

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.imagefilter.activities.main.MainActivity
import com.example.imagefilter.databinding.ActivityEditImageBinding

class EditImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        displayImagePreview()
    }

    private fun displayImagePreview(){
        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let { imageUri->
            val inputStream=contentResolver.openInputStream(imageUri)
            val bitmap=BitmapFactory.decodeStream(inputStream)
            binding.imagePreview.setImageBitmap(bitmap)
            binding.imagePreview.visibility=View.VISIBLE
        }
    }
    private fun setListener(){
        binding.imageBack.setOnClickListener{
        onBackPressed()
        }

    }
}