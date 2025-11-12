package com.example.meumercadojusto.model

data class Produto(
    val id: Int,
    val nome: String,
    val preco: Double,
    val mercadoId: Int
)