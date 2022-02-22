package com.edvora.edvora.repository

import android.content.Context
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.pojo.User
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import java.io.InputStream

interface EdvoraRemoteRepository {

    suspend fun getAllData(context: Context): Response<List<Ride>>?

    suspend fun getUser(context: Context): Response<User>?
}