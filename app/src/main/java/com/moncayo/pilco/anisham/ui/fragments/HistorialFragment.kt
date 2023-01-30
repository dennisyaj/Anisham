package com.moncayo.pilco.anisham.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.FragmentHistorialBinding

class HistorialFragment : Fragment() {

    private lateinit var binding: FragmentHistorialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistorialBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()

        init()
    }

    private fun init() {

    }
}