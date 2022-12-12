package com.mycalories.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mycalories.model.Rutinas

@Database(entities = [Rutinas::class], version = 1, exportSchema = false)
abstract class RutinasDatabase : RoomDatabase(){

    abstract fun rutinasDao() : RutinaDao

    companion object{
        @Volatile
        private var INSTANCE: RutinasDatabase? = null

        fun getDatabase(context: Context) : RutinasDatabase{
            val local = INSTANCE
            if(local != null){
                return local
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RutinasDatabase::class.java,
                    "rutina_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}