package com.moncayo.pilco.anisham.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moncayo.pilco.anisham.databinding.ActivityDetalleAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.ui.adapters.GeneroAdapter
import com.moncayo.pilco.anisham.userCase.anime.SearchUC
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalleAnime : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleAnimeBinding
    private val adapter = GeneroAdapter()
    private var listGeneros = ArrayList<String>()
    var item: AnimeMCResponse = AnimeMCResponse()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleAnimeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        var json = ""
        loadGeneros()

        intent.extras.let {
            json = it?.getString("item").toString()
            if (json != "") {
                item = Gson().fromJson(
                    json,
                    AnimeMCResponse::class.java
                )
            }
        }
        initItem(item)
        listGeneros = item.genders as ArrayList<String>
        loadGeneros()
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

    private fun initItem(item: AnimeMCResponse) {
        binding.tvTituloAnime.text = item.title.toString()
        binding.tvTextoSecundario.text = item.sinopsis.toString()
        Picasso.get().load(item.image).into(binding.ivBanner)
        binding.tvSoporte.text = item.rating
        binding.rbPuntaje.rating = item.rating!!.toFloat()
        binding.fbtnAgregar.setOnClickListener {
            saveItem(item)
        }
    }

    private fun saveItem(item:AnimeMCResponse) {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                SearchUC().saveAnime(item)
            }
        }
    }
}