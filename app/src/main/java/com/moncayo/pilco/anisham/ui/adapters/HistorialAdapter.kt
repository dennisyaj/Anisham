package com.moncayo.pilco.anisham.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ItemAnimeBinding
import com.moncayo.pilco.anisham.databinding.ItemGeneroBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB
import com.moncayo.pilco.anisham.ui.activities.Reproductor
import com.moncayo.pilco.anisham.userCase.historial.HistorialUC
import com.squareup.picasso.Picasso

class HistorialAdapter() : RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {
    var dataList: List<HistorialDB> = emptyList()
    lateinit var itemClick: (HistorialDB) -> Unit
    lateinit var itemClickBuscar: (HistorialDB) -> Unit

    class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemAnimeBinding = ItemAnimeBinding.bind(view)
        fun render(item: HistorialDB,itemClick: (HistorialDB) -> Unit,itemClickBuscar: (HistorialDB) -> Unit) {
            binding.tvNombre.text = item.itemHistorial.anilist?.title?.romaji
            binding.tvEpisodio.text = item.itemHistorial.episode.toString()
            binding.tvSimilitud.text =
                String.format("%.2f", item.itemHistorial.similarity!! * 100) + " %"
            Picasso.get().load(item.itemHistorial.image).into(binding.ivFoto)
            binding.btnBorrar.setOnClickListener {
                itemClick(item)
            }
            binding.btnBuscar.setOnClickListener { itemClickBuscar(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HistorialViewHolder(inflater.inflate(R.layout.item_anime, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        holder.render(dataList[position],itemClick,itemClickBuscar)

      }
}