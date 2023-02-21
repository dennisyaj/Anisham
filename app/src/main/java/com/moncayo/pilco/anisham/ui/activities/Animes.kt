package com.moncayo.pilco.anisham.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moncayo.pilco.anisham.databinding.ActivityAnimesBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import com.moncayo.pilco.anisham.ui.adapters.AnimeAdapter
import kotlinx.coroutines.launch
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.userCase.monosChinos.MonosChinosUC
import com.moncayo.pilco.anisham.userCase.myAnimeList.MyAnimeListUC
import com.moncayo.pilco.anisham.utils.Variables
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

            //filtro de contenido de edad y que no se repitan
            var listAnimesPosibles = data.result!!.distinctBy { it.anilist?.id }
            if (!Variables.contenidoNSFW) {
                listAnimesPosibles =
                    listAnimesPosibles.filter { it.anilist?.isAdult == false }
            }
            val itemClick = fun(item: Result) {
                val job = lifecycleScope.launch {

                    //obtenemos la informacion de los animes en español
                    var tmp = MonosChinosUC().generarDetalles(item)

                    //verificamos si encontramos informacion en espaniol sino buscamos en ingles
                    if (tmp == null) {

                        //obtenemmos informacion en ingles
                        var data = MyAnimeListUC().obtenerAnime(item.anilist?.idMal.toString())
                        if (data != null) {
                            tmp = MyAnimeListUC().convertirMyanimeList(data)
                        }
                    }
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
            adapter.dataList = listAnimesPosibles
            binding.rvResultadoBusqueda.adapter = adapter
            binding.rvResultadoBusqueda.layoutManager = LinearLayoutManager(
                this@Animes,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}