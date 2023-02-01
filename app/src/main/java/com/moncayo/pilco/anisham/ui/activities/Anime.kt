package com.moncayo.pilco.anisham.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.moncayo.pilco.anisham.databinding.ActivityAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist
import com.moncayo.pilco.anisham.userCase.anime.SearchUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Anime : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeBinding
    var item= Anilist()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun saveItem() {
        lifecycleScope.launch(Dispatchers.IO){
           SearchUC().saveAnime(item)
        }


    }
}