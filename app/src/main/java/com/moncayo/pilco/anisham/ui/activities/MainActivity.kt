package com.moncayo.pilco.anisham.ui.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.moncayo.pilco.anisham.databinding.ActivityMainBinding
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

    fun adicional() {
        binding.btnLogin.setOnClickListener {
            val contextView = binding.tfUserLogin
            lifecycleScope.launch {
                val user = UserUC().getUser(binding.tfUserLogin.text.toString())
                if (user?.status == "active") {
                    Snackbar.make(contextView, "Si esta disponible", Snackbar.LENGTH_SHORT).show()
                    var intent = Intent(this@MainActivity, PrincipalActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(contextView, "No esta disponible", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }

}