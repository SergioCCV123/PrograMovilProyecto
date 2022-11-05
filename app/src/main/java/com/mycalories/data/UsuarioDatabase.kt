package com.mycalories.data

import android.content.Context
import androidx.room.Database
import androidx.room.*
import com.mycalories.model.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class UsuarioDatabase : RoomDatabase() {

    abstract fun usuarioDao() : UsuarioDao

    companion object{
        @Volatile
        private var INSTANCE: UsuarioDatabase? = null

        fun getDatabase(context: Context) : UsuarioDatabase{
            val local = INSTANCE
            if(local != null){
                return local
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioDatabase::class.java,
                    "calories_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}