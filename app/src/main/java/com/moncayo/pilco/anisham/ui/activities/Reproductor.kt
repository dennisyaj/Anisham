package com.moncayo.pilco.anisham.ui.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer.Builder
import com.moncayo.pilco.anisham.databinding.ActivityReproductorBinding

private lateinit var binding: ActivityReproductorBinding

class Reproductor : AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReproductorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        extraerDatos()
    }

    fun extraerDatos() {
        var item = ""
        intent.extras.let {
            item = it?.getString("url").toString()
            if (item != "") {
                cargarVideo(item)
            }
        }
    }

    private fun cargarVideo(item: String) {
        val player: ExoPlayer = Builder(this@Reproductor).build()
        binding.pvVideo.player = player
        val videoUri = Uri.parse(item)
        val mediaItem: MediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

}