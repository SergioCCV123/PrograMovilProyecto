package com.mycalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mycalories.model.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun addUsuario(usuario: Usuario)

    @Update(onConflict=OnConflictStrategy.IGNORE)
    suspend fun updateUsuario(usuario: Usuario)

    @Delete
    suspend fun deleteUsuario(usuario: Usuario)

    @Query("SELECT * FROM USUARIO WHERE UID = :uid")
    fun getUser(uid: String) : Usuario
}