package com.moncayo.pilco.anisham.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.moncayo.pilco.anisham.databinding.ActivityAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.userCase.anime.SearchUC
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Anime : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeBinding
    var item = Anilist()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onStart() {
        super.onStart()
        var json: String = ""
        var item: AnimeMCResponse = AnimeMCResponse()

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
    }

    private fun initItem(item: AnimeMCResponse) {
        binding.tvTituloAnime.text = item.title.toString()
        binding.tvTextoSecundario.text = item.sinopsis.toString()
        Picasso.get().load(item.image).into(binding.ivBanner)
    }

    private fun saveItem() {
        lifecycleScope.launch(Dispatchers.IO) {
            SearchUC().saveAnime(item)
        }
    }
}