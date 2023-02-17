package com.moncayo.pilco.anisham.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.FragmentHistorialBinding
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB
import com.moncayo.pilco.anisham.ui.adapters.HistorialAdapter
import com.moncayo.pilco.anisham.userCase.historial.HistorialUC
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