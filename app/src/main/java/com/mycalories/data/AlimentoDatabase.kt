package com.mycalories.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mycalories.model.Alimento

@Database(entities = [Alimento::class], version = 1, exportSchema = false)
abstract class AlimentoDatabase: RoomDatabase() {

    abstract fun alimentoDao() : AlimentoDao

    companion object{
        @Volatile
        private var INSTANCE: AlimentoDatabase? = null

        fun getDatabase(context: Context) : AlimentoDatabase{
            val local = INSTANCE
            if (local != null){
                return local
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlimentoDatabase::class.java,
                    "alimento_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}