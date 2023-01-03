package com.moncayo.pilco.anisham

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.moncayo.pilco.anisham.databinding.ActivityMainBinding
import com.moncayo.pilco.anisham.databinding.ActivityPrincipalBinding
import java.io.IOException


private lateinit var binding: ActivityPrincipalBinding
class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
         adicional()

    }
     fun adicional(){

         binding.btnSelect.setOnClickListener {
             val intent = Intent(Intent.ACTION_PICK).apply {
                 type = "image/*"
             }
             startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SELECT_IMAGE_REQUEST_CODE)
         }
            }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri = data.data
            try {
                val selectedImageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                binding.ivSelectedImage.setImageBitmap(selectedImageBitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val SELECT_IMAGE_REQUEST_CODE = 1
    }
    
}