package com.example.meumercadojusto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mercado")
data class Mercado(
    @PrimaryKey
    val id: Int,
    val nome: String,
    val endereco: String,
    val total: Double = 0.0
)