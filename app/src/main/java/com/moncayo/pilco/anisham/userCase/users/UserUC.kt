package com.moncayo.pilco.anisham.userCase.users

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.UserEndPoint
import com.moncayo.pilco.anisham.model.entities.api.user.User
import com.moncayo.pilco.anisham.model.repositories.APIRepository

class UserUC {
    suspend fun getUser(user: String): User? {
        var data: User? = null
        try {
            val service = APIRepository().buildUserService(UserEndPoint::class.java)
            val response = service.getUser(user)
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