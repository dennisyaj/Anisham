package com.moncayo.pilco.anisham.ui.adapters

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ItemAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.ui.activities.Reproductor
import com.moncayo.pilco.anisham.userCase.historial.HistorialUC
import com.moncayo.pilco.anisham.utils.Variables
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeAdapter :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    var dataList: List<Result> = emptyList()
    lateinit var itemClick: (Result) -> Unit
    lateinit var itemClickPlay: (Result) -> Unit
    lateinit var itemClickBuscar: (Result) -> Unit

    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemAnimeBinding = ItemAnimeBinding.bind(view)
        fun render(
            item: Result,
            itemClick: (Result) -> Unit,
            itemClickPlay: (Result) -> Unit,
            itemClickBuscar: (Result) -> Unit,
        ) {
            binding.btnBorrar.visibility = View.GONE
            binding.btnBuscar.visibility = View.GONE
            binding.tvNombre.text = item.anilist?.title?.romaji
            binding.tvEpisodio.text = item.episode.toString()
            binding.tvSimilitud.text = String.format("%.2f", item.similarity!! * 100) + " %"
            binding.ivPlay.visibility = View.VISIBLE
            Picasso.get().load(item.image).into(binding.ivFoto)
            binding.btnBuscar.setOnClickListener { itemClickBuscar(item) }
            binding.ivPlay.setOnClickListener { itemClickPlay(item) }
            itemView.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(inflater.inflate(R.layout.item_anime, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.render(dataList[position], itemClick, itemClickPlay, itemClickBuscar)
    }

    override fun getItemCount(): Int = dataList.size
}