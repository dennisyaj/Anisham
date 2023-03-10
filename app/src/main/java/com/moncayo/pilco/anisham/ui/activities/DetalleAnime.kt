package com.moncayo.pilco.anisham.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ActivityDetalleAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.ui.adapters.GeneroAdapter
import com.moncayo.pilco.anisham.userCase.historial.HistorialUC
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.moncayo.pilco.anisham.model.entities.api.anime.Result

class DetalleAnime : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleAnimeBinding
    private val adapter = GeneroAdapter()
    private var listGeneros = ArrayList<String>()
    var item: AnimeMCResponse = AnimeMCResponse()
    var item2: Result = Result()
    var guardado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleAnimeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        var json = ""
        var json2 = ""

        intent.extras.let {
            json = it?.getString("item").toString()
            json2 = it?.getString("idDB").toString()
            if (json != "") {
                item = Gson().fromJson(
                    json,
                    AnimeMCResponse::class.java
                )
            }
            if (json2 != "") {
                item2 = Gson().fromJson(
                    json2, Result::class.java
                )
            }
        }
        buscarEnHistorial(item2)
        initItem(item, item2)
        listGeneros = item.genders as ArrayList<String>
        loadGeneros()
    }

    private fun buscarEnHistorial(item: Result) {
        lifecycleScope.launch(Dispatchers.Main) {
            guardado = withContext(Dispatchers.IO) {
                HistorialUC().buscarHistorial(item.anilist!!.idMal!!.toInt())
            }
            if (guardado) {
                binding.fbtnAgregar.icon =
                    ContextCompat.getDrawable(this@DetalleAnime, R.drawable.baseline_turned_in_24)
                binding.fbtnAgregar.text = "Guardado"
            }
        }
    }

    private fun loadGeneros() {
        adapter.dataList = listGeneros
        binding.rvGenero.adapter = adapter
        binding.rvGenero.layoutManager = LinearLayoutManager(
            this@DetalleAnime,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun initItem(item: AnimeMCResponse, item2: Result) {
        binding.tvTituloAnime.text = item.title.toString()
        binding.tvTextoSecundario.text = item.sinopsis.toString()
        Picasso.get().load(item.image).into(binding.ivBanner)
        binding.tvSoporte.text = item.rating
        binding.rbPuntaje.rating = item.rating!!.toFloat()
        binding.fbtnAgregar.setOnClickListener { saveItem(item2) }
        binding.btnBuscar.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://www.google.com/search?q=Online " + item.title)
            startActivity(intent)
        }
    }

    private fun saveItem(item2: Result) {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                HistorialUC().saveAnime(item2)
            }
            binding.fbtnAgregar.icon =
                ContextCompat.getDrawable(this@DetalleAnime, R.drawable.baseline_turned_in_24)
            binding.fbtnAgregar.text = "Guardado"
        }
    }
}