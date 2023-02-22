package com.moncayo.pilco.anisham.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.FragmentHistorialBinding
import com.moncayo.pilco.anisham.databinding.FragmentSeleccionImagenBinding
import com.moncayo.pilco.anisham.ui.activities.Animes
import com.moncayo.pilco.anisham.ui.adapters.AnimeAdapter
import com.moncayo.pilco.anisham.ui.adapters.HistorialAdapter
import com.moncayo.pilco.anisham.userCase.anime.SearchUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

class SeleccionImagenFragment : Fragment() {

    private lateinit var binding: FragmentSeleccionImagenBinding
    var selectedImageUri = Uri.parse("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSeleccionImagenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
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
            binding.cpCarga.visibility = View.VISIBLE
            lifecycleScope.launch() {
                var tmpBusqueda = SearchUC().getAnimeWithImg(
                    File(
                        getRealPathFromURI(selectedImageUri)
                    )
                )
                var intent = Intent(activity?.baseContext, Animes::class.java)
                val json = Gson().toJson(tmpBusqueda)
                intent.putExtra("listaDatos", json)
                startActivity(intent)
                binding.cpCarga.visibility = View.GONE
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            try {
                val selectedImageBitmap =
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImageUri)
                binding.ivSelectedImage.setImageBitmap(
                    selectedImageBitmap
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getRealPathFromURI(uri: Uri): String? {
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        return if (cursor == null) {
            uri.path
        } else {
            cursor.use {
                it.moveToFirst()
                val idx = it.getColumnIndex(MediaStore.Images.Media.DATA)
                it.getString(idx)
            }
        }
    }

    companion object {
        private const val SELECT_IMAGE_REQUEST_CODE = 1
    }
}