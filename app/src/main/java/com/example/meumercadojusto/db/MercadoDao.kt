package com.example.meumercadojusto.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.meumercadojusto.model.Mercado

@Dao
interface MercadoDao {
    @Insert
    suspend fun insertAll(mercados: List<Mercado>)
    
    @Query("SELECT * FROM mercado")
    suspend fun findAll(): List<Mercado>
    
    @Query("SELECT * FROM mercado ORDER BY total ASC LIMIT 1")
    suspend fun findMelhor(): Mercado?
    
    @Query("DELETE FROM mercado")
    suspend fun deleteAll()
}