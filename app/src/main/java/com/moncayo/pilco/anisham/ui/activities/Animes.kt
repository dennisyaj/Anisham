package com.moncayo.pilco.anisham.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moncayo.pilco.anisham.databinding.ActivityAnimesBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import com.moncayo.pilco.anisham.ui.adapters.AnimeAdapter
import kotlinx.coroutines.launch
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.userCase.monosChinos.MonosChinosUC
import kotlinx.coroutines.Dispatchers

private lateinit var binding: ActivityAnimesBinding

class Animes : AppCompatActivity() {

    private val adapter = AnimeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        var json: String = ""
        var item: SearchResponse = SearchResponse()
        var json2: String = ""
        var item2: Result = Result()
        intent.extras.let {
            json = it?.getString("listaDatos").toString()
            if (json != "") {
                item = Gson().fromJson(
                    json, SearchResponse::class.java
                )
            }
        }
        loadAnimes(item)
    }

    private fun loadAnimes(data: SearchResponse) {
        lifecycleScope.launch(Dispatchers.Main) {
            val uniqueAnimes = data.result!!.distinctBy { it.anilist?.id }
            val itemClick = fun(item: Result) {
                val job = lifecycleScope.launch {
                    var tmp = MonosChinosUC().generarDetalles(item)
                    val json = Gson().toJson(tmp)
                    val json2 = Gson().toJson(item)
                    val toShowInfo = Intent(
                        this@Animes,
                        DetalleAnime::class.java
                    )
                    toShowInfo.putExtra("item", json)
                    toShowInfo.putExtra("idDB", json2)
                    startActivity(toShowInfo)
                }
            }

            adapter.itemClick = itemClick
            adapter.dataList = uniqueAnimes
            binding.rvResultadoBusqueda.adapter = adapter
            binding.rvResultadoBusqueda.layoutManager = LinearLayoutManager(
                this@Animes,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}