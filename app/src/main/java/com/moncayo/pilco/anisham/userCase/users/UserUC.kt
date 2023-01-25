package com.moncayo.pilco.anisham.userCase.users

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.UserEndPoint
import com.moncayo.pilco.anisham.model.entities.api.User
import com.moncayo.pilco.anisham.model.repositories.UserAPIRepository

class UserUC {
    suspend fun getUser(user: String): User? {
        var data: User? = null
        try {
            val service = UserAPIRepository().getRetrofit()
            val response = service.create(UserEndPoint::class.java).getUser(user)
            if (response.isSuccessful) {
                data = response.body()!!
            } else {
                throw Exception("Fracaso en la conexion")
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return data
    }
}