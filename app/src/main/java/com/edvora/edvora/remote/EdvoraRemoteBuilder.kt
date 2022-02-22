package com.edvora.edvora.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EdvoraRemoteBuilder {

    // if you want to using RestFul Api
    companion object{
        private const val EDVORA_BASE_URL: String = "..."
        fun edvoraBuilder(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(EDVORA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}