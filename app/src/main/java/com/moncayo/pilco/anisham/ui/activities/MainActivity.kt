package com.moncayo.pilco.anisham.ui.activities


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ActivityMainBinding
import com.moncayo.pilco.anisham.userCase.users.UserUC
import kotlinx.coroutines.launch

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adicional()
    }

    private fun adicional() {
        binding.btnLogin.setOnClickListener {
            validarUsuario(binding.tfUserLogin.text.toString())
        }
    }

    private fun validarUsuario(userId: String) {
        val contextView = binding.tfUserLogin

        lifecycleScope.launch {
            val user = UserUC().getUser(userId)
            if (user?.status == "active") {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle(R.string.titleLogin)
                    .setMessage(resources.getString(R.string.MensajeOkLogin) + user?.status)
                    .setNeutralButton("Aceptar") { dialog, which ->
                    }
                    .setPositiveButton("Ingresar") { dialog, which ->
                        var intent = Intent(this@MainActivity, PrincipalActivity::class.java)
                        startActivity(intent)
                    }.show()
            } else {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle(R.string.titleLogin)
                    .setMessage(resources.getString(R.string.MensajeOkLogin) + user?.status)
                    .setNeutralButton("Reintentar") { dialog, which ->
                        validarUsuario(binding.tfUserLogin.text.toString())
                    }
                    .setNegativeButton("Cancelar") { dialog, which ->
                        // Respond to negative button press
                    }.show()
            }
        }
    }

}