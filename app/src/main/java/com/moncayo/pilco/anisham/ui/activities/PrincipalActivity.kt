package com.moncayo.pilco.anisham.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ActivityPrincipalBinding
import com.moncayo.pilco.anisham.ui.fragments.HistorialFragment
import com.moncayo.pilco.anisham.ui.fragments.SeleccionImagenFragment

private lateinit var binding: ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
        cargarFrameInicial()
        nombreUsuario()
    }

    fun nombreUsuario() {
        var nombre = ""
        intent.extras.let {
            nombre = it?.getString("user").toString()
        }
        binding.tvUser.text = "Usuario: " + nombre
    }

    private fun cargarFrameInicial() {
        fragmentVisibility(SeleccionImagenFragment())
    }

    fun init() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    fragmentVisibility(SeleccionImagenFragment())
                    true
                }

                R.id.item_2 -> {
                    fragmentVisibility(HistorialFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Manejo de fragmentos
    private fun fragmentVisibility(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flFragmentPrincipal.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}