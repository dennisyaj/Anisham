package com.moncayo.pilco.anisham.ui.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ActivityMainBinding
import com.moncayo.pilco.anisham.model.entities.api.User
import com.moncayo.pilco.anisham.userCase.users.SearchUC
import com.moncayo.pilco.anisham.userCase.users.UserUC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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