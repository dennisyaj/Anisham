package com.moncayo.pilco.anisham.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.moncayo.pilco.anisham.databinding.ActivityPrincipalBinding
import com.moncayo.pilco.anisham.userCase.users.SearchUC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.log


private lateinit var binding: ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adicional()

    }

    fun adicional() {

        binding.btnSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/jpeg"
            }
            startActivityForResult(
                Intent.createChooser(intent, "Selecciona una imagen"), SELECT_IMAGE_REQUEST_CODE
            )
        }
        binding.btnSend.setOnClickListener {
            var urlImagen = "https://images.plurk.com/7G7hr9zVoeMbRM1MrcL9to.jpg"
            lifecycleScope.launch() {
                var tmpBusqueda = SearchUC().getAnime(urlImagen)
                var intent = Intent(this@PrincipalActivity, DetallesAnime::class.java)
                val json = Gson().toJson(tmpBusqueda)
                Log.i("datos", json)
                intent.putExtra("listaDatos", json)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri = data.data
            try {
                val selectedImageBitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                binding.ivSelectedImage.setImageBitmap(selectedImageBitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getRealPathFromURI(contentURI: Uri): String {
        val result: String
        val cursor = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.path!!
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    companion object {
        private const val SELECT_IMAGE_REQUEST_CODE = 1
    }

}