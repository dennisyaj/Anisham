package com.moncayo.pilco.anisham.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moncayo.pilco.anisham.databinding.ActivityDetallesAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import com.moncayo.pilco.anisham.ui.adapters.AnimeAdapter
import kotlinx.coroutines.launch

private lateinit var binding: ActivityDetallesAnimeBinding

class DetallesAnime : AppCompatActivity() {

    private val listAnimes = SearchResponse()
    private val adapter = AnimeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesAnimeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()

        var json: String = ""
        var item: SearchResponse = SearchResponse()

        intent.extras.let {
            json = it?.getString("listaDatos").toString()
            Log.i("detalle", json)
            if (json != "") {
                item = Gson().fromJson(
                    json, SearchResponse::class.java
                )
            }
        }
        loadAnimes(item)
    }

    private fun loadAnimes(data: SearchResponse) {
        lifecycleScope.launch {
            val uniqueAnimes = data.result!!.distinctBy { it.anilist.id }
            adapter.dataList = uniqueAnimes
            binding.rvResultadoBusqueda.adapter = adapter
            binding.rvResultadoBusqueda.layoutManager = LinearLayoutManager(
                this@DetallesAnime,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}