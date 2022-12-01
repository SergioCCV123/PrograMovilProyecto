package com.example.recetas.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mycalories.model.Receta

@Dao
interface RecetaDao {

    //las funciones de bajo nivel para hacer un CRUD
    //create Read Update Delete

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReceta(receta: Receta)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun udpateReceta(receta: Receta)

    @Delete
    suspend fun deleteReceta(receta: Receta)

    @Query("SELECT * FROM RECETA")
    fun getRecetas() : LiveData<List<Receta>>
}