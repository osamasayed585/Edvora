package com.edvora.edvora.repository

import android.content.Context
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.pojo.User
import com.edvora.edvora.remote.EdvoraApi
import com.edvora.edvora.remote.EdvoraRemoteBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImplementation: EdvoraRemoteRepository {

    private var api: EdvoraApi? = null

    init {
        api = EdvoraApi()
    }

    override suspend fun getAllData(context: Context): Response<List<Ride>>? {
        return withContext(Dispatchers.IO){
            api?.getAllData(context)
        }
    }

    override suspend fun getUser(context: Context): Response<User>? {
        return withContext(Dispatchers.IO){
            api?.getUser(context)
        }
    }

}