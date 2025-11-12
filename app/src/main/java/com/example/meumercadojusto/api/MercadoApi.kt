package com.example.meumercadojusto.api

import com.example.meumercadojusto.model.Mercado
import com.example.meumercadojusto.model.Produto
import retrofit2.http.GET

interface MercadoApi {
    @GET("produtos")
    suspend fun getProdutos(): List<Produto>
    
    @GET("mercados")
    suspend fun getMercados(): List<Mercado>
}