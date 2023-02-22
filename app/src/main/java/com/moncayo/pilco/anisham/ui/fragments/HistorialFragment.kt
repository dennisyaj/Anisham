package com.moncayo.pilco.anisham.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.FragmentHistorialBinding
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB
import com.moncayo.pilco.anisham.ui.activities.DetalleAnime
import com.moncayo.pilco.anisham.ui.adapters.HistorialAdapter
import com.moncayo.pilco.anisham.userCase.historial.HistorialUC
import com.moncayo.pilco.anisham.userCase.monosChinos.MonosChinosUC
import com.moncayo.pilco.anisham.userCase.myAnimeList.MyAnimeListUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistorialFragment : Fragment() {
    private lateinit var binding: FragmentHistorialBinding
    private val adapter = HistorialAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHistorialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        lifecycleScope.launch(Dispatchers.Main) {
            var historial = withContext(Dispatchers.IO) {
                HistorialUC().obtenerHistorial()
            }
            val itemClickBorrar = @SuppressLint("NotifyDataSetChanged")
            fun(item: HistorialDB) {
                lifecycleScope.launch(Dispatchers.Main) {
                    var tmp = withContext(Dispatchers.IO) {
                        HistorialUC().borrar(item.idMal)
                        adapter.dataList = HistorialUC().obtenerHistorial()
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            val itemClickBuscar = fun(item: HistorialDB) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("https://www.google.com/search?q=Online " + item.itemHistorial.anilist?.title?.romaji)
                startActivity(intent)
            }
            adapter.itemClickBuscar = itemClickBuscar
            adapter.itemClick = itemClickBorrar
            adapter.dataList = historial
            binding.rvHistorial.adapter = adapter
            binding.rvHistorial.layoutManager = LinearLayoutManager(
                activity?.baseContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}