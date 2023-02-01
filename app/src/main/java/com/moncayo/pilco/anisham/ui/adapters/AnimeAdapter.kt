package com.moncayo.pilco.anisham.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ItemAnimeBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.squareup.picasso.Picasso

class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    var dataList: List<Result> = emptyList()
    lateinit var itemClick: (Result) -> Unit

    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemAnimeBinding = ItemAnimeBinding.bind(view)
        fun render(item: Result, itemClick: (Result) -> Unit) {
            binding.tvNombre.text = item.anilist.title?.romaji
            binding.tvEspisodio.text = item.episode.toString()
            binding.tvSimilitud.text = String.format("%.2f", item.similarity * 100) + " %"
            Picasso.get().load(item.image).into(binding.ivFoto)
            itemView.setOnClickListener {
                itemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(inflater.inflate(R.layout.item_anime, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.render(dataList[position], itemClick)
    }

    override fun getItemCount(): Int = dataList.size
}