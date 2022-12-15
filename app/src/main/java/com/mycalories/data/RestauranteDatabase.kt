package com.mycalories.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mycalories.model.Restaurante

@Database(entities = [Restaurante::class], version = 1, exportSchema = false)
abstract class RestauranteDatabase: RoomDatabase(){

    abstract fun restauranteDao() : RestauranteDao

    companion object{
        @Volatile
        private var INSTANCE: RestauranteDatabase? = null

        fun getDatabase(context: Context) : RestauranteDatabase{
            val local = INSTANCE
            if (local != null){
                return local
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestauranteDatabase::class.java,
                    "restaurante_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}