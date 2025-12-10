package com.example.meumercadojusto.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/jvarb1/API-FAKE/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val mercadoApi: RemoteMercadoApiDataSource = retrofit.create(RemoteMercadoApiDataSource::class.java)
}