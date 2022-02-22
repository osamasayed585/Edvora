package com.edvora.edvora.remote

import android.content.Context
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.pojo.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException
import java.io.InputStream

class EdvoraApi() {


    fun getAllData(context: Context): Response<List<Ride>>? {
        var json: String? = null
        json = try {
            val inputStream: InputStream = context.assets.open("response.json")
            val size: Int = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            inputStream.close()
            String(byteArray, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        val gson = Gson()
        return Response.success(
            gson.fromJson(json, object : TypeToken<List<Ride>>() {}.type)

        )
    }

    fun getUser(context: Context): Response<User>? {
        var json: String? = null
        json = try {
            val inputStream: InputStream = context.assets.open("user.json")
            val size: Int = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            inputStream.close()
            String(byteArray, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        val gson = Gson()
        return Response.success(
            gson.fromJson(json, User::class.java)
        )
    }
}