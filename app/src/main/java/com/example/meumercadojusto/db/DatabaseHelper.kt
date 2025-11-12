package com.example.meumercadojusto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meumercadojusto.model.Mercado

@Database(
    version = 1,
    entities = [Mercado::class]
)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun mercadoDao(): MercadoDao
    
    companion object {
        fun getInstance(context: Context): DatabaseHelper {
            return Room.databaseBuilder(
                context,
                DatabaseHelper::class.java,
                "mercado.db"
            ).build()
        }
    }
}