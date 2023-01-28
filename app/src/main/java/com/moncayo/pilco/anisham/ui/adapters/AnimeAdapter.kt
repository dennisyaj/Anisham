package com.moncayo.pilco.anisham.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ItemAnimeBinding
import com.squareup.picasso.Picasso

class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    var dataList: List<com.moncayo.pilco.anisham.model.entities.api.Result> = emptyList()

    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: ItemAnimeBinding = ItemAnimeBinding.bind(view)
        fun render(item: com.moncayo.pilco.anisham.model.entities.api.Result) {
            binding.tvNombre.text = item.filename
            binding.tvEspisodio.text = item.episode.toString()
            binding.tvSimilitud.text = item.similarity.toString()
            Picasso.get().load(item.image).into(binding.ivFoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(inflater.inflate(R.layout.item_anime, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.render(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}