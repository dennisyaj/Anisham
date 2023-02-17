package com.moncayo.pilco.anisham.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ItemAnimeBinding
import com.moncayo.pilco.anisham.databinding.ItemGeneroBinding
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB

class HistorialAdapter() : RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {
    var dataList: List<HistorialDB> = emptyList()

    class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemAnimeBinding = ItemAnimeBinding.bind(view)
        fun render(item: HistorialDB) {
            binding.tvNombre.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HistorialViewHolder(inflater.inflate(R.layout.item_anime, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        holder.render(dataList[position])
    }
}