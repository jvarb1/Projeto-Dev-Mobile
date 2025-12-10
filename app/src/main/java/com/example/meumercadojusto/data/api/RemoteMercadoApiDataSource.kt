package com.example.meumercadojusto.data.api

import com.example.meumercadojusto.model.Mercado
import com.example.meumercadojusto.model.Produto
import retrofit2.http.GET

interface RemoteMercadoApiDataSource {
    @GET("produtos")
    suspend fun getProdutos(): List<Produto>
    
    @GET("mercados")
    suspend fun getMercados(): List<Mercado>
}